package com.chen.annotation.annotationdemo;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class FindParent {

    String[] node = {"James", "Mary", "John", "Patricia", "Robert", "Jennifer", "Michael", "Linda", "William", "Elizabeth", "David", "Barbara", "Richard", "Susan", "Joseph", "Jessica", "Thomas", "Sarah", "Charles", "Karen", "Christopher", "Nancy", "Daniel", "Margaret", "Matthew", "Lisa", "Anthony", "Betty", "Donald", "Dorothy", "Mark", "Sandra", "Paul", "Ashley", "Steven", "Kimberly", "Andrew", "Donna", "Kenneth", "Emily", "Joshua", "Michelle", "George", "Carol", "Kevin", "Amanda", "Brian", "Melissa", "Edward", "Deborah", "Ronald", "Stephanie", "Timothy", "Rebecca", "Jason", "Laura", "Jeffrey", "Sharon", "Ryan", "Cynthia", "Jacob", "Kathleen", "Gary", "Helen", "Nicholas", "Amy", "Eric", "Shirley", "Stephen", "Angela", "Jonathan", "Anna", "Larry", "Brenda", "Justin", "Pamela", "Scott", "Nicole", "Brandon", "Ruth", "Frank", "Katherine", "Benjamin", "Samantha", "Gregory", "Christine", "Samuel", "Emma", "Raymond", "Catherine", "Patrick", "Debra", "Alexander", "Virginia", "Jack", "Rachel", "Dennis", "Carolyn", "Jerry", "Janet", "Tyler", "Maria", "Aaron", "Heather", "Jose", "Diane", "Henry", "Julie", "Douglas", "Joyce", "Adam", "Victoria", "Peter", "Kelly", "Nathan", "Christina", "Zachary", "Joan", "Walter", "Evelyn", "Kyle", "Lauren", "Harold", "Judith", "Carl", "Olivia", "Jeremy", "Frances", "Keith", "Martha", "Roger", "Cheryl", "Gerald", "Megan", "Ethan", "Andrea", "Arthur", "Hannah", "Terry", "Jacqueline", "Christian", "Ann", "Sean", "Jean", "Lawrence", "Alice", "Austin", "Kathryn", "Joe", "Gloria", "Noah", "Teresa", "Jesse", "Doris", "Albert", "Sara", "Bryan", "Janice", "Billy", "Julia", "Bruce", "Marie", "Willie", "Madison", "Jordan", "Grace", "Dylan", "Judy", "Alan", "Theresa", "Ralph", "Beverly", "Gabriel", "Denise", "Roy", "Marilyn", "Juan", "Amber", "Wayne", "Danielle", "Eugene", "Abigail", "Logan", "Brittany", "Randy", "Rose", "Louis", "Diana", "Russell", "Natalie", "Vincent", "Sophia", "Philip", "Alexis", "Bobby", "Lori", "Johnny", "Kayla", "Bradley", "Jane"};

//    String[] node = {"Jacob", "Emily", "Michael", "Madison", "Joshua", "Hannah", "Matthew", "Emma", "Andrew", "Ashley", "Christopher", "Alexis", "Joseph", "Samantha", "Nicholas", "Sarah", "Daniel", "Abigail", "William", "Olivia", "Ethan", "Elizabeth", "Anthony", "Alyssa", "Ryan", "Jessica", "Tyler", "Grace", "David", "Lauren", "John", "Taylor", "Alexander", "Kayla", "James", "Brianna", "Zachary", "Isabella", "Brandon", "Anna", "Jonathan", "Victoria", "Dylan", "Sydney", "Justin", "Megan", "Christian", "Rachel", "Samuel", "Jasmine", "Benjamin", "Natalie", "Austin", "Sophia", "Nathan", "Chloe", "Noah", "Morgan", "Jose", "Hailey", "Logan", "Jennifer", "Robert", "Destiny", "Kevin", "Julia", "Thomas", "Kaitlyn", "Gabriel", "Haley", "Caleb", "Katherine", "Jordan", "Nicole", "Hunter", "Alexandra", "Cameron", "Savannah", "Kyle", "Maria", "Jason", "Amanda", "Elijah", "Mackenzie", "Aaron", "Stephanie", "Jack", "Allison", "Connor", "Mia", "Isaiah", "Brooke", "Luke", "Jordan", "Isaac", "Jenna", "Evan", "Rebecca", "Eric", "Mary", "Brian", "Makayla", "Angel", "Faith", "Juan", "Andrea", "Adam", "Katelyn", "Jackson", "Paige", "Mason", "Madeline", "Luis", "Gabrielle", "Charles", "Michelle", "Sean", "Kaylee", "Aidan", "Sara", "Gavin", "Kimberly", "Alex", "Amber", "Nathaniel", "Trinity", "Bryan", "Zoe", "Carlos", "Caroline", "Steven", "Sierra", "Ian", "Ava", "Jesus", "Vanessa", "Timothy", "Kylie", "Cody", "Danielle", "Cole", "Erin", "Seth", "Alexa", "Adrian", "Ella", "Devin", "Autumn", "Lucas", "Jacqueline", "Richard", "Lily", "Patrick", "Jada", "Blake", "Bailey", "Julian", "Shelby", "Jared", "Melissa", "Chase", "Gabriella", "Trevor", "Courtney", "Sebastian", "Leah", "Antonio", "Riley", "Miguel", "Marissa", "Garrett", "Angelina", "Jeremiah", "Isabel", "Jesse", "Leslie", "Owen", "Angela", "Xavier", "Ariana", "Alejandro", "Claire", "Mark", "Lillian", "Dominic", "Christina", "Jayden", "Catherine", "Jeremy", "Maya", "Jaden", "Audrey", "Bryce", "Aaliyah", "Victor", "Molly", "Jake", "Jocelyn", "Hayden", "Breanna", "Diego", "Melanie", "Carson", "Mariah", "Colin", "Jade", "Riley", "Katie", "Wyatt", "Briana", "Kenneth", "Arianna", "Carter", "Diana", "Tanner", "Evelyn", "Aiden", "Kathryn", "Dakota", "Laura", "Tristan", "Alexandria", "Marcus", "Sofia", "Jorge", "Amy", "Stephen", "Gabriela", "Henry", "Caitlin", "Dalton", "Kelsey", "Paul", "Isabelle", "Spencer", "Angel", "Liam", "Madelyn", "Vincent", "Avery", "Kaleb", "Lindsey", "Edward", "Kelly", "Oscar", "Margaret", "Joel", "Cheyenne", "Eduardo", "Sabrina", "Landon", "Mikayla", "Brendan", "Adriana", "Parker", "Alicia", "Colton", "Cassandra", "Maxwell", "Daniela", "Jeffrey", "Cassidy", "Grant", "Jillian", "Alexis", "Kennedy", "George", "Brittany", "Ivan", "Miranda", "Collin", "Tiffany", "Shane", "Ana", "Peter", "Lydia", "Brayden", "Erica", "Ashton", "Mya", "Nicolas", "Brooklyn", "Derek", "Amelia", "Ricardo", "Alexia", "Jalen", "Daisy", "Travis", "Mckenzie", "Francisco", "Caitlyn", "Alan", "Skylar", "Caden", "Summer", "Gage", "Angelica", "Omar", "Crystal", "Cristian", "Sophie", "Preston", "Karen", "Bradley", "Gracie", "Shawn", "Ashlyn", "Brady", "Kiara", "Devon", "Erika", "Fernando", "Hope", "Colby", "Gianna", "Erik", "Bianca", "Javier", "Heather", "Kaden", "Veronica", "Josiah", "Valerie", "Andres", "Chelsea", "Max", "Natalia", "Cesar", "Kylee", "Manuel", "Karina", "Gregory", "Alondra", "Levi", "Naomi", "Mario", "Jordyn", "Johnathan", "Jamie", "Edgar", "Meghan", "Conner", "Peyton", "Mitchell", "Abby", "Clayton", "Payton", "Nolan", "Juliana", "Micah", "Kendall", "Damian", "Bethany", "Raymond", "Carly", "Braden", "Cynthia", "Jonah", "Kate", "Taylor", "Hayley", "Cooper", "Delaney", "Trenton", "Kristen", "Wesley", "Jasmin", "Corey", "Monica", "Edwin", "Karla", "Dustin", "Alejandra", "Dillon", "Mckenna", "Scott", "Shannon", "Erick", "Maggie", "Peyton", "Rylee", "Emmanuel", "Kyla", "Marco", "Nevaeh", "Sergio", "Valeria", "Dawson", "Hanna", "Martin", "Brenda", "Hector", "Julianna", "Giovanni", "Diamond", "Roberto", "Michaela", "Donovan", "Reagan", "Eli", "Aubrey", "Brett", "Esmeralda", "Alec", "Makenzie", "Jakob", "Giselle", "Abraham", "Jazmin", "Harrison", "Rebekah", "Andre", "Ariel", "Andy", "Ruby", "Malik", "Desiree", "Drew", "Lizbeth", "Ruben", "Charlotte", "Ty", "Sadie", "Damien", "Kaitlin", "Jaylen", "Adrianna", "Cade", "Kyra", "Pedro", "Jayla", "Calvin", "Genesis", "Elias", "Alana", "Josue", "Addison", "Leonardo", "Mallory", "Frank", "Britney", "Malachi", "Nadia", "Phillip", "Amaya", "Ronald", "Kara", "Chandler", "Elena", "Trey", "Julie", "Bryson", "Camryn", "Gerardo", "Lindsay", "Skyler", "Kendra", "Chance", "Macy", "Rafael", "Aliyah", "Trent", "Claudia", "Casey", "Alison", "Zane", "Ellie", "Griffin", "Elise", "Avery", "Holly", "Dominick", "Eva", "Derrick", "Selena", "Miles", "Joanna", "Raul", "Jazmine", "Johnny", "Raven", "Israel", "Savanna", "Armando", "Nina", "Darius", "Haylee", "Troy", "Makenna", "Enrique", "Mariana", "Donald", "Fatima", "Keith", "Allyson", "Marcos", "Asia", "Payton", "Guadalupe", "Allen", "Katelynn", "Simon", "Cameron", "Dante", "Nancy", "Jaime", "Serena", "Zackary", "Layla", "Julio", "Vivian", "Brock", "Lucy", "Kobe", "April", "Brenden", "Liliana", "Keegan", "Kathleen", "Drake", "Camille", "Lance", "Brittney", "Oliver", "Josephine", "Kameron", "Katrina", "Jonathon", "Carmen", "Mathew", "Cierra", "Brody", "Kailey", "Alberto", "Sandra", "Gustavo", "Carolina", "Philip", "Celeste", "Jace", "Cindy", "Jimmy", "Tatiana", "Fabian", "Kristina", "Dennis", "Jaden", "Jerry", "Kirsten", "Marc", "Cecilia", "Cory", "Zoey", "Brennan", "Skyler", "Ayden", "Patricia", "Louis", "Serenity", "Angelo", "Casey", "Camden", "Yesenia", "Corbin", "Tessa", "Saul", "Wendy", "Danny", "Kira", "Kyler", "Anastasia", "Roman", "Rachael", "Curtis", "Miriam", "Tucker", "Heaven", "Myles", "Christine", "Bailey", "Bridget", "Chad", "Tara", "Tony", "Priscilla", "Arturo", "Alaina", "Pablo", "Tori", "Damon", "Kassandra", "Randy", "Alissa", "Lane", "Natasha", "Gary", "Madeleine", "Braxton", "Mercedes", "Douglas", "Josie", "Kai", "Lauryn", "Albert", "Esther", "Grayson", "Jayden", "Larry", "Lilly", "Nickolas", "Dakota", "Emanuel", "Paris", "Quinn", "Kayleigh", "Lorenzo", "Clara", "Darren", "Sidney", "Alfredo", "Kiana", "Theodore", "Nayeli", "Bryant", "Shayla", "Tyson", "Brenna", "Axel", "Paola", "Zion", "Alexus", "Leo", "Marisa", "Joe", "Melody", "Lukas", "Imani", "Santiago", "Denise", "Tristen", "Nia", "Emilio", "Emilee", "Jaxon", "Ciara", "Kristopher", "Logan", "Ramon", "Rose", "Ismael", "Ashanti", "Ricky", "Meredith", "Russell", "Annie", "Arthur", "Ashlee", "Zachery", "Bryanna", "Moises", "Callie", "Salvador", "Kamryn", "Jay", "Eleanor", "Carl", "Anne", "Ernesto", "Heidi", "Quentin", "Ashlynn", "Jayson", "Ashleigh", "Walter", "Annabelle", "Ezekiel", "Ruth", "Micheal", "Clarissa", "Esteban", "Daniella", "Dallas", "Julissa", "Nikolas", "Dominique", "Lawrence", "Laila", "Morgan", "Annika", "Marvin", "Allie", "Tommy", "Dana", "Maximilian", "Kassidy", "Mateo", "Lisa", "Deandre", "Eliza", "Marshall", "Harley", "Abel", "Rosa", "Isiah", "Whitney", "Ali", "Helen", "Brent", "Hallie", "Jaiden", "Cristina", "Camron", "Shania", "Braeden", "Anahi", "Reece", "Kaylie", "Maurice", "Marina", "Jeffery", "Talia", "Terry", "Kristin", "Cayden", "Jadyn", "Amir", "Carolyn", "Keaton", "Alayna", "Branden", "Deanna", "Jarrett", "Georgia", "Eddie", "India", "Dean", "Yasmin", "Mauricio", "Tabitha", "Orlando", "Elisabeth", "Rodney", "Fiona", "Davis", "Piper", "Jon", "Tiana", "Justice", "Ivy", "Xander", "Aniya", "Brayan", "Halle", "Rodrigo", "Raquel", "Jamal", "Teresa", "Hugo", "Hailee", "Kody", "Madalyn", "Felix", "Emely", "Kade", "Jayda", "Shaun", "Eden", "Roger", "Linda", "Chris", "Jenny", "Conor", "Jaqueline", "Skylar", "Krystal", "Reese", "Lesly", "Trevon", "Iris", "Craig", "Virginia", "Maximus", "Lexi", "Zachariah", "Gillian", "Weston", "Gloria", "Javon", "Tatum", "Adan", "Marisol", "Brendon", "Perla", "Kelvin", "Sasha", "Charlie", "Monique", "Walker", "Baylee", "Melvin", "Kiley", "Bobby", "Kaitlynn", "Graham", "Aurora", "Guillermo", "Eliana", "Quinton", "Angie", "Landen", "Camila", "Tyrese", "Nora", "Julius", "Lacey", "Billy", "Katlyn", "Zackery", "Bella", "Demetrius", "Haleigh", "Issac", "Leilani", "Holden", "Alivia", "Ezra", "Francesca", "Jaylon", "Tamia", "Desmond", "Destinee", "Frederick", "Renee", "Nelson", "Ashton", "Tate", "Noelle", "Khalil", "Paulina", "Dorian", "Deja", "Rene", "Aniyah", "Jessie", "Phoebe", "Zander", "Regan", "Aden", "Alisha", "Johnathon", "Viviana", "Jaylin", "Ayanna", "Gerald", "Sage", "Felipe", "Gina", "Reginald", "Madisyn", "Elliot", "Carla", "Roy", "Leila", "Ahmad", "Alina", "Steve", "Reese", "Kolby", "Alice", "Malcolm", "Kaleigh", "Jadon", "Genevieve", "Terrance", "Rylie", "Hudson", "Jane", "Bennett", "Martha", "Willie", "Carissa", "Noel", "Cheyanne", "Kendall", "Marie", "Kristian", "Kiera", "Nathanael", "Brooklynn", "Harley", "Tania", "Sam", "Skye", "Jermaine", "Amari", "Dane", "Gisselle", "Reid", "Stella", "Damion", "Meagan", "Asher", "Taryn", "Uriel", "Janet", "Clay", "Madyson", "Declan", "Itzel", "Marquis", "Kaylin", "Bruce", "Jessie", "Noe", "Kyleigh", "Allan", "Tia", "Quincy", "Malia", "Rylan", "Brynn", "Kenny", "Alyson", "Osvaldo", "Tamara", "Beau", "Yasmine", "Terrell", "Cora", "Blaine", "Precious", "Kayden", "Emilie", "Kaiden", "Krista", "Sawyer", "Mckayla", "Franklin", "Jacquelyn", "Solomon", "Abbey", "Davion", "Ellen", "Emiliano", "Justice", "Tomas", "Cara", "Joaquin", "Kiersten", "Yahir", "Macie", "Todd", "Izabella", "Darian", "Kierra", "Jonas", "Johanna", "Rodolfo", "Karissa", "Triston", "Mikaela", "Toby", "Lucia", "Moses", "Carley", "Francis", "Kasey", "Byron", "Joy", "Reed", "Susan", "Rogelio", "Cassie", "Alvin", "Pamela", "Mohamed", "Maddison", "Kendrick", "Elaina", "Silas", "Janelle", "Wilson", "Dulce", "Devan", "Larissa", "Jaquan", "Alanna", "Tyrone", "Sharon", "Mekhi", "Kali", "Nasir", "Elisa", "Warren", "Tiara", "Deven", "Anya", "Joey", "Charity", "Leon", "Sarai", "Randall", "Jaiden", "Gilberto", "Athena", "Duncan", "Miracle", "Jude", "Brandy", "Cedric", "Brandi", "Ramiro", "Ryan", "Amari", "Tyler", "Cyrus", "America", "Luca", "Amya", "Alfonso", "Angelique", "Pierce", "Theresa", "Marlon", "Mayra", "Ahmed", "Abbigail", "Lee", "Kailee", "Elliott", "Marlene", "Dayton", "Irene", "Dale", "Kaley", "Braydon", "Maritza", "Easton", "Fernanda", "Wade", "Ainsley", "Jerome", "Joselyn", "Leonel", "Aileen", "Harry", "Bailee", "Ross", "Anika", "Ronnie", "Carlie", "Deshawn", "Sonia", "Jamie", "Rhiannon", "Stanley", "Janae", "Tristin", "Marilyn", "Darrell", "Jaclyn", "Rolando", "Simone", "Mohammad", "Sylvia", "Trace", "Kelsie", "Will", "Colleen", "Addison", "Ryleigh", "Jaheim", "Lesley", "Alonzo", "Lena", "Terrence", "Madelynn", "Garret", "Litzy", "Adolfo", "Raegan", "Tobias", "Araceli", "Jaydon", "Abbie", "Jordon", "Kaila", "Kieran", "Hunter", "Isaias", "Emilia", "Leonard", "Macey", "Coby", "Jaelyn", "Tyrell", "Felicity", "Jarod", "Ann", "Rohan", "Lorena", "Jamari", "Lexie", "Jasper", "Alma", "Ben", "Tatyana", "Eugene", "Nataly", "Ariel", "Isis", "Wayne", "Madilyn", "Tyree", "Aspen", "Neil", "Melina", "Mohammed", "Valentina", "Alvaro", "Eve", "Orion", "Maia", "Colten", "Justine", "Rudy", "Daphne", "Gilbert", "Kenya", "Jayce", "Luz", "Vicente", "Cadence", "Davon", "Julianne", "Aldo", "Janiya", "Keenan", "Deborah", "Jaron", "Zaria", "Efrain", "Nathalie", "Ulises", "Kailyn", "Ray", "Arielle", "Ezequiel", "Tianna", "Gunnar", "Isabela", "Ernest", "Cristal", "Izaiah", "Aimee", "Coleman", "Helena", "Everett", "Barbara", "Brennen", "Willow", "Harold", "Nyla", "Romeo", "Samara", "Jaxson", "Kaya", "Sage", "Judith", "Dominique", "Reyna", "Donte", "Noemi", "Johan", "Chasity", "River", "Chelsey", "Quintin", "Sydnee", "Ryder", "Shyanne", "Sterling", "Tanya", "Keshawn", "Clare", "Jameson", "Marley", "Koby", "Tess", "Lincoln", "Liberty", "Kadin", "Lila", "Brice", "Regina", "Titus", "Tyra", "Humberto", "Laney", "Dwayne", "Haylie", "Kolton", "Zoie", "Agustin", "Frances", "Cristopher", "Juliet", "Cruz", "Lilian", "Lewis", "Brisa", "Jamison", "Felicia", "Devonte", "Alessandra", "Mike", "Adrienne", "Kellen", "Thalia", "Kevon", "Edith", "Antoine", "Keira", "Karl", "Esperanza", "Jairo", "Brielle", "Darien", "Ally", "Freddy", "Hayden", "Darryl", "Skyla", "Phoenix", "Elaine", "Elisha", "Jaida", "Dashawn", "Karlee", "Grady", "Jazlyn", "Jett", "Kaelyn", "Jamar", "Janessa", "Kareem", "Mara", "Cullen", "Gwendolyn", "Devyn", "Nichole", "Julien", "Kaylyn", "Nathanial", "Savanah", "Caiden", "Paula", "Glenn", "Stacy", "Davin", "Violet", "Ralph", "Breana", "Aron", "Leticia", "Nehemiah", "Corinne", "Keyshawn", "Anaya", "Kole", "Maci", "Ibrahim", "Celia", "Salvatore", "Estrella", "Muhammad", "Hailie", "Alfred", "Carina", "Gunner", "Juliette", "Kane", "Harmony", "Moshe", "Ximena", "Octavio", "Sienna", "August", "Mollie", "Markus", "Laurel", "Rashad", "Yadira", "Dominik", "Toni", "Ignacio", "Tayler", "Elvis", "Mariela", "Irvin", "Delilah", "Keon", "Teagan", "Sidney", "Carlee", "Junior", "Hana", "Shamar", "Alisa", "Karson", "Haven", "Marquise", "Halie", "Stefan", "Aisha", "Alonso", "Rosemary", "Nigel", "Tina", "Elian", "Jimena", "Asa", "Carrie", "Zechariah", "Lea", "Omarion", "Eileen", "Anderson", "Lillie", "Gael", "Keely", "Judah", "Karlie", "Derick", "Cayla", "Kamron", "Micah", "Daquan", "Antonia", "Roderick", "Mariam", "Rowan", "Jazmyn", "Jean", "Jolie", "Jair", "Kayley", "Raphael", "Jewel", "Rigoberto", "Kelli", "Dillan", "Leanna", "Clarence", "Devin", "Greyson", "Kenzie", "Dandre", "Karli", "Emmett", "Aracely", "Jaylan", "Ebony", "Sheldon", "Presley", "Rory", "Amani", "Dangelo", "Adeline", "Darnell", "Aleah", "Jarred", "Ciera", "Garrison", "Arely", "Camren", "Nya", "Kurt", "Ayana", "Misael", "Lana", "Keagan", "Stephany", "Gianni", "Kennedi", "Rhett", "Annette", "Porter", "Destiney", "Jabari", "Alena", "Layne", "Lia", "Brooks", "Lizeth", "Armani", "Giovanna", "Talon", "Ansley", "Omari", "Yvette", "Finn", "Blanca", "Howard", "Shayna", "Lamar", "Desirae", "Josh", "Joyce", "Clinton", "Kianna", "Donavan", "Micaela", "Justus", "Sydni", "Anton", "Christian", "Ari", "Aubree", "Gordon", "Kaia", "Milton", "Donna", "Draven", "Maeve", "Deon", "Ingrid", "Jefferson", "Quinn", "Demarcus", "Shaniya", "Reynaldo", "Salma", "Conrad", "Candace", "Abram", "Amara", "Baby", "Lara", "Darion", "Nicolette", "German", "Damaris", "Giancarlo", "Kaylynn", "Geoffrey", "Nikki", "Jovan", "Yazmin", "Santos", "Essence", "Bernard", "Abigayle", "Brycen", "Destini", "Roland", "Maribel", "Heath", "Katharine", "Brodie", "Sheila", "Savion", "Dayana", "Brad", "Aria", "Gideon", "Hazel", "Jarrod", "Kathy", "Tariq", "Katarina", "Jaeden", "Cecelia", "Rocco", "Jenifer", "Estevan", "Rebeca", "Reagan", "Greta", "Hamza", "Amira", "Zakary", "Katelin", "Gaven", "Aliya", "Travon", "Maura", "Maximillian", "Lyric", "Tylor", "Monserrat", "Quinten", "Jaylin", "Jaren", "Sandy", "Alexzander", "Shyann", "Amarion", "Stacey", "Frankie", "Aiyana", "Perry", "Margarita", "Nico", "Bria", "Sincere", "Annalise", "Emerson", "Calista", "Alexandro", "Parker", "Antony", "Scarlett", "Jordy", "Jalyn", "Alessandro", "Mattie", "Hassan", "Elyse", "Isai", "Karly", "Guadalupe", "Myah", "Xzavier", "Shea", "Keyon", "Marianna", "Gonzalo", "Lizette", "Stephan", "Chaya", "Elmer", "Madisen", "Norman", "Melany", "Jorden", "Emmalee", "Maverick", "Catalina", "Tristian", "Celine", "Chaz", "Liana", "Denzel", "Ericka", "Braiden", "Devon", "Jacoby", "Nyah", "Clark", "Robin", "Keanu", "Kasandra", "Darrius", "Gia", "Maddox", "London", "Tre", "Carson", "Dallin", "Ayla", "Fredrick", "Princess", "Jovani", "Hadley", "Leroy", "Kayli", "Korey", "Moriah", "Blaze", "Anissa", "Cale", "Shaylee", "Shannon", "Lilliana", "Winston", "Jacey", "Dexter", "Lyndsey", "Sammy", "Elle", "Matteo", "Lexus", "Dion", "Susana", "Seamus", "Gretchen", "Marcel", "Alysa", "Braedon", "Tierra", "Stone", "Brianne", "Alijah", "Alize", "Dimitri", "Estefania", "Brenton", "Montana", "Cordell", "Cali", "Darin", "Joana", "Clifford", "Beatriz", "Earl", "Noelia", "Ronaldo", "Tracy", "Deangelo", "Reilly", "Barry", "Aryanna", "Giovanny", "Iliana", "Thaddeus", "Dorothy", "Nestor", "Kaylah", "Reuben", "Elissa", "Samir", "Dylan", "Korbin", "Savana", "Adonis", "Kourtney", "Kelton", "Janice", "Alden", "Devyn", "Antwan", "Robyn", "Sonny", "Elsa", "Nick", "Ryann", "Houston", "Alia", "Remington", "Carol", "Javion", "Sydnie", "Arnold", "Jakayla", "Destin", "Arlene", "Dylon", "Jaime", "Abdullah", "Raina", "Jan", "Taliyah", "Dario", "Aliza", "Lamont", "Jasmyn", "Shayne", "Carli", "Adrien", "Diane", "Deonte", "Anita", "Nathen", "Rayna", "Luciano", "Shirley", "Blaise", "Shakira", "Kasey", "Rocio", "Keven", "Penelope", "Bruno", "Jamya", "Lawson", "Jaycee", "Kian", "Alexandrea", "Jamel", "Kenna", "Cortez", "Graciela", "Cornelius", "Kenia", "Josef", "Dasia", "Ellis", "Annabel", "Shea", "Christiana", "Nikhil", "Karley", "Reilly", "Christa", "Heriberto", "Kallie", "Stephon", "Trista", "Jagger", "Frida", "Semaj", "Saige", "Vaughn", "Paloma", "Jarvis", "Abigale", "Marcelo", "Kacie", "Augustus", "Casandra", "Domenic", "Ashly", "Barrett", "Patience", "Isaak", "Iyana", "Ronan", "Jackeline", "London", "Mireya", "Kenyon", "Annabella", "Raymundo", "Magdalena", "Dwight", "Lola", "Irving", "Taniya", "Marquez", "Fabiola", "Kylan", "Marlee", "Hugh", "Abril", "Tyshawn", "Belen", "Prince", "Selina", "Gino", "Elyssa", "Jovany", "Kaiya", "Deshaun", "Laci", "Guy", "Abagail", "Bernardo", "Nyasia", "Trever", "Ashtyn", "Efren", "Joelle", "Chaim", "Kellie", "Mikel", "Kaliyah", "Kale", "Kendal", "Maxim", "Meadow", "Paxton", "Mandy", "Kennedy", "Kalyn", "Benny", "Jailyn", "Fidel", "Taya", "Rahul", "Yuliana", "Oswaldo", "Kya", "Gaige", "Christy", "Bret", "Dalia", "Darrin", "Sally", "Stuart", "Jaidyn", "Trevion", "Shawna", "Gavyn", "Berenice", "Samson", "Aylin", "Carlo", "Jaylynn", "Bo", "Candice", "Nash", "Armani", "Khalid", "Amiya", "Forrest", "Reina", "Austen", "Rachelle", "Kurtis", "Loren", "Vance", "Johana", "Latrell", "Dayanara", "Milo", "Kadence", "Rey", "Kaydence", "Pranav", "Madalynn", "Arjun", "Aubrie", "Jakobe", "Makaila", "Daryl", "Miah", "Cristobal", "Alexys", "Darwin", "Darlene", "Jadyn", "Sarahi", "Kory", "Danna", "Haden", "Dianna", "Clifton", "Bryana", "Shemar", "Baby", "Rylee", "Amina", "Zaire", "Reanna", "Kent", "Evelin", "Glen", "Journey", "Jamarion", "Lina", "Aryan", "Jana", "Ean", "Myra", "Zain", "Yessenia", "Simeon", "Citlali", "Justyn", "Jazmyne", "Syed", "Kaci", "Turner", "Joslyn", "Waylon", "Brionna", "Jamil", "Anjali", "Layton", "Alex", "Yosef", "Katy", "Sabastian", "Makena", "Vernon", "Chyna", "Andreas", "Jaliyah", "Aditya", "Meaghan", "Darrion", "Melinda", "Jamir", "Emerson", "Jovanni", "Shreya", "Jevon", "Janiyah", "Zavier", "Jaylene", "Niko", "Kelsi", "Jase", "Galilea", "Pierre", "Yareli", "Valentin", "Giana", "Freddie", "Hillary", "Gannon", "Celina", "Ulysses", "Maegan", "Austyn", "Sarina", "Santino", "Maliyah", "Carlton", "Yoselin", "Adriel", "Samira", "Malakai", "Sheridan", "Marques", "Areli", "Colt", "Kristine", "Jahiem", "Jeanette", "Joan", "Chandler", "Cael", "Allyssa", "Bradyn", "Jacklyn", "Mitchel", "Drew", "Cason", "Isabell", "Devante", "Keyla", "Garett", "Libby", "Ethen", "Norah", "Kelly", "Chanel", "Treyton", "Laisha", "Lonnie", "Kari", "Aydan", "Brook", "Terence", "Yolanda", "Duane", "Unique", "Javen", "Rita", "Don", "Jaquelin", "Zack", "Camilla", "Kirk", "Silvia", "Jovanny", "Kaela", "Fred", "Lisbeth", "Aubrey", "Roxana", "Eliseo", "Kacey", "Kristofer", "Kinsey", "Rickey", "Norma", "Neal", "Jalynn", "Yair", "Sky", "Jordyn", "Sherlyn", "Sullivan", "Aryana", "Raven", "Iyanna", "Immanuel", "Caitlynn", "Trystan", "Bridgette", "Ryley", "Bonnie", "Bronson", "Jaylyn", "Kanye", "Sonya", "Lloyd", "Maleah", "Jeff", "Vanesa", "Damarion", "Mira", "Yusuf", "Caleigh", "Mariano", "Lisette", "Fletcher", "Delia", "Benito", "Deasia", "Rocky", "Maryam", "Giovani", "Priscila", "Kyree", "Darby"};

    List<Parent> parents = new ArrayList<>();

    Map<String, Parent> map = new HashMap<>();

    @Before
    public void setUp() {
//        Map<String, String> map = new HashMap<>();

        Random random = new Random(System.currentTimeMillis());

//        List<String> list = new ArrayList<>();
//
//        Arrays.stream(node).forEach((name) -> {
//            if (random.nextFloat() <= 0.1 && !list.contains(name)) {
//                list.add(name);
//                parents.add(new Parent(name));
//            }
//        });

        parents.add(new Parent(node[0]));
        map.put(node[0], new Parent(node[0]));

        for (int i = 1; i < node.length; i++) {
            String name = node[i];

            if (random.nextFloat() <= 0.05) {
                parents.add(new Parent(name));
                map.put(name, new Parent(name));

            } else {
                int pi = random.nextInt(parents.size());
                parents.add(new Parent(name, node[pi]));

                map.put(name, new Parent(name, node[pi]));

            }
        }

//        Arrays.stream(node).forEach((name) -> {
//            if (map.size() <= 0 || random.nextFloat() <= 0.1) {
////                map.put(name, null);
//                parents.add(new Parent(name));
//            } else {
//                int pi = random.nextInt(map.size());
//
//            }
//        });
    }

    void generateTree() {
        Map<String, String> map = new HashMap<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {

            Integer index = random.nextInt((node.length - 1 - 0) + 1) + 0;

            String name = node[index];

            if (map.containsKey(name)) {
                Integer index1 = random.nextInt((node.length - 1 - 0) + 1) + 0;
                if (index.equals(index1)) {
                    if (index1 == 0) {
                        ++index1;
                    } else if (index1 == node.length - 1) {
                        --index1;
                    }

                    if (!node[index1].equals(map.get(name)) && !node[index1].equals(name))
                        map.put(node[index1], name);

                }

            } else {


                map.put(node[index], null);
            }
        }

        map.forEach((key, it) -> {
            System.out.println("key :" + key + "  ,it:" + it);
        });

    }

    @Test
    public void findParent() {
        ReadJava readJava = new ReadJava();

        long start = System.currentTimeMillis();

//        parents.stream().filter(r -> r.getParent() == null || r.getParent().equals("")).forEach(r -> readJava.fixFind(parents, r));

        map.forEach((key, it) -> {

            it.setValue(fixFindParent(map, it));
        });

//        readJava.fixFindParent(parents);

        long end = System.currentTimeMillis();

        System.out.printf("took %d ms\n", end - start);


    }

    public String fixFindParent(Map<String, Parent> parentMap, Parent parent) {
        String par = parent.getParent();

        if (par == null) {
            if (parent.getValue() == null)
                parent.setValue(parent.getName());
        } else {
            Parent p = parentMap.get(par);

            if (p.getParent() != null) {

                if (p.getValue() == null) {

                    return fixFindParent(parentMap, p);
                } else {
                    return p.getValue() + "." + parent.getName();
                }

            } else if (p.getParent() == null) {
                if (p.getValue() != null)

                    return p.getValue() + "." + parent.getName();
                else
                    return p.getName();

            }
        }
        return null;
    }


}
