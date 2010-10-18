package app;


%%

%standalone

/* Variables y metodos auxiliares. */
%{
private Farm farm;
String auxString;
Animal auxAnimal;

		
%}

/* Acciones que se hacen en el constructor. */
%init{
System.out.println("Comienzo del programa. ");
farm = new Farm();

%init} 

/* Accion al encontrar EOF. */
%eof{

System.out.println("Eliminando referencia de los animales ...");
farm.updateDietReferences();
System.out.println("Referencias eliminadas");

System.out.println("---------------------------------------------------------------------------------------------------");
System.out.println("Animales no comen");
farm.animalsNotEat();

System.out.println("---------------------------------------------------------------------------------------------------");
System.out.println("Animales que no tiene comidad en la granja!");
System.out.println(farm.animalsHaventFood());

System.out.println("---------------------------------------------------------------------------------------------------");
System.out.println("Comida que no esta en la granja!");
System.out.println(farm.absentPlants());

System.out.println("---------------------------------------------------------------------------------------------------");
System.out.println("Dietas que no tien el granjero");
System.out.println(farm.absentDiet());

System.out.println("---------------------------------------------------------------------------------------------------");
//System.out.println("Plantas disponibles" + farm.getAvailablePlants());
farm.printAnimals();
farm.printFood();

System.out.println("Fin de programa.");
%eof} 

%s ANIMALS_CONTEXT
%s ANIMAL_CONTEXT
%s ANIMAL_ITEM_CONTEXT
%s PLANTS_CONTEXT
%s PLANT_CONTEXT
%s INIT_CONTEXT
%s FINAL_CONTEXT

WHITESPACE = [ \t\n]
ANIMALS_START = "Animales:"
ANIMAL = [a-zA-Z ]+
ANIMAL_IDEM = "idem"{WHITESPACE}+{ANIMAL}
DOT = "."
COMMA = ","
PLANTS_START = "Plantas:"
PLANT = ({ANIMAL}{WHITESPACE}*{ANIMAL})+

%%

						{WHITESPACE}*	{
										}
<YYINITIAL>				{ANIMALS_START}	{
											yybegin(ANIMALS_CONTEXT);
										
										}
<ANIMALS_CONTEXT>		{PLANTS_START}	{
											yybegin(PLANTS_CONTEXT);
										}
<ANIMALS_CONTEXT>		{ANIMAL}:		{
											yybegin(ANIMAL_CONTEXT);
											
											auxString = yytext().toLowerCase().trim();
											auxString = auxString.replace(":", "");
											auxAnimal = new Animal(auxString);
											farm.addAnimal(auxAnimal);
											
										}
<ANIMAL_CONTEXT>		{ANIMAL}|{ANIMAL_IDEM}	
										{
											yybegin(ANIMAL_ITEM_CONTEXT);
											auxAnimal.addFoodToDiet(new Food(yytext().toLowerCase()));										
											
										}
<ANIMAL_CONTEXT>		{DOT}			{
											yybegin(ANIMALS_CONTEXT);
											
										}
<ANIMAL_ITEM_CONTEXT>	{DOT}			{
											yybegin(ANIMALS_CONTEXT);
									
										}
<ANIMAL_ITEM_CONTEXT>	{COMMA}			{
											yybegin(ANIMAL_CONTEXT);
										
										}
<PLANTS_CONTEXT>		{PLANT}			{
											yybegin(PLANT_CONTEXT);
											farm.addFood(new Food(yytext().toLowerCase()));
										
										}

<PLANT_CONTEXT>		{COMMA}				{
											yybegin(PLANTS_CONTEXT);
										}		
		