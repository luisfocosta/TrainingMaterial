package com.amica.hr;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory, hard-coded database of jobs, departments, and employees.
 *
 * @author Will Provost
 */
public class Data {

	public Map<Integer,Job> jobs = new HashMap<>();
	public Map<Integer,Department> departments = new HashMap<>();
	public Map<Integer,Employee> employees = new HashMap<>();
	
	/**
	 * Simply instantiate, and the constructor builds out three maps,
	 * much like database tables with primary keys and then instances of
	 * {@link Job}, {@link Department}, and {@link Employee} as values.
	 * The three maps are public fields, to simplify later usage.
	 * There is a one-way relationship navigable from employee to job,
	 * and a two-way relationship between department and employee.
	 */
	public Data() {

		jobs.put(1, new Job("President", 80000, 100000));
		jobs.put(2, new Job("Vice President", 70000, 90000));
		jobs.put(3, new Job("Director", 60000, 80000));
		jobs.put(4, new Job("Manager", 50000, 70000));
		jobs.put(5, new Job("Supervisor", 40000, 60000));
		jobs.put(6, new Job("Administrative Assistant", 25000, 45000));
		jobs.put(7, new Job("Secretary", 20000, 40000));
		jobs.put(8, new Job("Lead Engineer", 30000, 40000));
		jobs.put(9, new Job("Engineer", 20000, 30000));
		jobs.put(10, new Job("Junior Engineer", 15000, 20000));
		jobs.put(11, new Job("DataBase Administrator", 80000, 120000));
		jobs.put(12, new Job("DataBase Operator", 20000, 40000));
		jobs.put(13, new Job("Systems Administrator", 60000, 80000));
		jobs.put(14, new Job("Systems Operator", 20000, 40000));
		jobs.put(15, new Job("Data Analyst", 30000, 50000));
		jobs.put(16, new Job("Project Lead", 70000, 90000));
		jobs.put(17, new Job("Senior Developer", 60000, 80000));
		jobs.put(18, new Job("Developer", 40000, 70000));
		jobs.put(19, new Job("Junior Developer", 30000, 50000));
		jobs.put(20, new Job("Product Evangelist", 50000, 70000));
		jobs.put(21, new Job("Senior Sales Rep", 35000, 55000));
		jobs.put(22, new Job("Account Rep", 30000, 50000));
		jobs.put(23, new Job("Sales Rep", 25000, 45000));
		jobs.put(24, new Job("Tester", 30000, 50000));
		jobs.put(25, new Job("Receptionist", 18000, 30000));
		jobs.put(26, new Job("Data Entry Clerk", 16000, 25000));
		jobs.put(27, new Job("Technical Writer", 25000, 50000));
		jobs.put(28, new Job("Trainer", 40000, 65000));
		jobs.put(29, new Job("COO", 70000, 90000));
		jobs.put(30, new Job("CFO", 70000, 90000));
		jobs.put(31, new Job("CIO", 70000, 90000));
		jobs.put(32, new Job("Test Designer", 40000, 60000));		
		
		departments.put(1, new Department("Research"));
		departments.put(2, new Department("Administration"));
		departments.put(3, new Department("Software Development"));
		departments.put(4, new Department("Hardware Development"));
		departments.put(5, new Department("Test And Integration"));
		departments.put(6, new Department("Sales"));
		departments.put(7, new Department("HR"));
		departments.put(8, new Department("Facilities"));
		departments.put(9, new Department("Operations"));
		
		employees.put(1001, new Employee("Patrick", "Acosta", departments.get(9), jobs.get(4), 61000));
		employees.put(1002, new Employee("John", "Amdell", departments.get(1), jobs.get(10), 15000));
		employees.put(1003, new Employee("Letitia", "Anderson", departments.get(2), jobs.get(26), 21000));
		employees.put(1004, new Employee("John", "Angel", departments.get(1), jobs.get(8), 30000));
		employees.put(1005, new Employee("John", "Ayer", departments.get(1), jobs.get(10), 15000));
		employees.put(1006, new Employee("Patrick", "Bailey", departments.get(9), jobs.get(6), 41000));
		employees.put(1007, new Employee("John", "Barrell", departments.get(1), jobs.get(4), 50000));
		employees.put(1008, new Employee("Vivian", "Baxter", departments.get(2), jobs.get(30), 82000));
		employees.put(1009, new Employee("Ty", "Beard", departments.get(3), jobs.get(17), 65000));
		employees.put(1010, new Employee("Luigi", "Berger", departments.get(5), jobs.get(16), 90000));
		employees.put(1011, new Employee("John", "Berries", departments.get(1), jobs.get(9), 20000));
		employees.put(1012, new Employee("Larry", "Best", departments.get(3), jobs.get(18), 65000));
		employees.put(1013, new Employee("John", "Bird", departments.get(1), jobs.get(9), 20000));
		employees.put(1014, new Employee("John", "Bivouc", departments.get(1), jobs.get(3), 60000));
		employees.put(1015, new Employee("Evelyn", "Booker", departments.get(9), jobs.get(14), 25000));
		employees.put(1016, new Employee("John", "Bowlin", departments.get(1), jobs.get(9), 20000));
		employees.put(1017, new Employee("Thelma", "Burgess", departments.get(6), jobs.get(3), 67000));
		employees.put(1018, new Employee("Glenda", "Burks", departments.get(3), jobs.get(7), 29000));
		employees.put(1019, new Employee("Elwood", "Calhoun", departments.get(2), jobs.get(26), 22000));
		employees.put(1020, new Employee("Walker", "Calhoun", departments.get(6), jobs.get(21), 38000));
		employees.put(1021, new Employee("Hugh", "Campbell", departments.get(6), jobs.get(23), 32000));
		employees.put(1022, new Employee("King", "Cardenas", departments.get(2), jobs.get(31), 84000));
		employees.put(1023, new Employee("Mariana", "Castillo", departments.get(4), jobs.get(17), 67000));
		employees.put(1024, new Employee("John", "Chee", departments.get(1), jobs.get(8), 30000));
		employees.put(1025, new Employee("Kareem", "Christian", departments.get(4), jobs.get(7), 25000));
		employees.put(1026, new Employee("Jami", "Church", departments.get(5), jobs.get(3), 65000));
		employees.put(1027, new Employee("John", "Crier", departments.get(1), jobs.get(3), 60000));
		employees.put(1028, new Employee("Elliot", "Cross", departments.get(9), jobs.get(14), 36000));
		employees.put(1029, new Employee("Travis", "David", departments.get(4), jobs.get(17), 75000));
		employees.put(1030, new Employee("Kay", "Dean", departments.get(6), jobs.get(23), 26000));
		employees.put(1031, new Employee("John", "deConnor", departments.get(1), jobs.get(9), 20000));
		employees.put(1032, new Employee("Donovan", "Dorsey", departments.get(5), jobs.get(24), 44000));
		employees.put(1033, new Employee("Wiley", "Durham", departments.get(6), jobs.get(20), 57000));
		employees.put(1034, new Employee("Lucas", "Espinoza", departments.get(6), jobs.get(23), 38000));
		employees.put(1035, new Employee("Ernestine", "Estrada", departments.get(7), jobs.get(5), 53000));
		employees.put(1036, new Employee("Georgia", "Farmer", departments.get(6), jobs.get(23), 28000));
		employees.put(1037, new Employee("Wendy", "Fleming", departments.get(6), jobs.get(21), 42000));
		employees.put(1038, new Employee("Edgar", "Frank", departments.get(9), jobs.get(12), 28000));
		employees.put(1039, new Employee("Ross", "Franks", departments.get(4), jobs.get(16), 68000));
		employees.put(1040, new Employee("Noble", "Frazier", departments.get(4), jobs.get(18), 62000));
		employees.put(1041, new Employee("Kitty", "French", departments.get(5), jobs.get(24), 43000));
		employees.put(1042, new Employee("John", "Garnet", departments.get(1), jobs.get(9), 20000));
		employees.put(1043, new Employee("John", "Gonzales", departments.get(1), jobs.get(8), 30000));
		employees.put(1044, new Employee("Latasha", "Goodwin", departments.get(6), jobs.get(23), 43000));
		employees.put(1045, new Employee("Jefferson", "Gray", departments.get(6), jobs.get(22), 41000));
		employees.put(1046, new Employee("Lesa", "Griffith", departments.get(4), jobs.get(18), 52000));
		employees.put(1047, new Employee("Eloise", "Gross", departments.get(3), jobs.get(18), 51000));
		employees.put(1048, new Employee("Napoleon", "Gross", departments.get(7), jobs.get(3), 76000));
		employees.put(1049, new Employee("John", "Gus", departments.get(1), jobs.get(10), 15000));
		employees.put(1050, new Employee("Leola", "Harris", departments.get(6), jobs.get(23), 32000));
		employees.put(1051, new Employee("Rowena", "Hebert", departments.get(9), jobs.get(14), 34000));
		employees.put(1052, new Employee("Leopoldo", "Holden", departments.get(5), jobs.get(24), 40000));
		employees.put(1053, new Employee("John", "Hood", departments.get(1), jobs.get(8), 30000));
		employees.put(1054, new Employee("Galen", "Hopper", departments.get(2), jobs.get(26), 25000));
		employees.put(1055, new Employee("Erick", "House", departments.get(4), jobs.get(4), 63000));
		employees.put(1056, new Employee("Rhonda", "Hunt", departments.get(3), jobs.get(16), 81000));
		employees.put(1057, new Employee("Shanna", "Hunter", departments.get(6), jobs.get(23), 43000));
		employees.put(1058, new Employee("Deloris", "Hurst", departments.get(4), jobs.get(18), 40000));
		employees.put(1059, new Employee("Orval", "Johns", departments.get(9), jobs.get(11), 120000));
		employees.put(1060, new Employee("John", "Jones", departments.get(1), jobs.get(8), 30000));
		employees.put(1061, new Employee("Dane", "King", departments.get(6), jobs.get(23), 28000));
		employees.put(1062, new Employee("John", "Bigboote", departments.get(1), jobs.get(2), 95000));
		employees.put(1063, new Employee("Tricia", "Larsen", departments.get(6), jobs.get(2), 81000));
		employees.put(1064, new Employee("Delmar", "Le", departments.get(3), jobs.get(4), 59000));
		employees.put(1065, new Employee("Dona", "Leonard", departments.get(2), jobs.get(26), 16000));
		employees.put(1066, new Employee("John", "Li", departments.get(1), jobs.get(8),30000));
		employees.put(1067, new Employee("Penny", "Pretty", departments.get(2), jobs.get(6), 24000));
		employees.put(1068, new Employee("Cruz", "Lyons", departments.get(8), jobs.get(7), 35000));
		employees.put(1069, new Employee("Randall", "Marsh", departments.get(6), jobs.get(23), 45000));
		employees.put(1070, new Employee("Tommy", "Martin", departments.get(6), jobs.get(21), 40000));
		employees.put(1071, new Employee("Emanuel", "Martinez", departments.get(9), jobs.get(12), 32000));
		employees.put(1072, new Employee("John", "Mary", departments.get(1), jobs.get(8), 30000));
		employees.put(1073, new Employee("Kory", "Maynard", departments.get(5), jobs.get(16), 83000));
		employees.put(1074, new Employee("Vanessa", "McConnell", departments.get(6), jobs.get(21), 48000));
		employees.put(1075, new Employee("Elias", "McCoy", departments.get(4), jobs.get(16), 72000));
		employees.put(1076, new Employee("Rhonda", "McKee", departments.get(6), jobs.get(22), 35000));
		employees.put(1077, new Employee("John", "Milton", departments.get(1), jobs.get(9), 20000));
		employees.put(1078, new Employee("John", "Mister", departments.get(1), jobs.get(9), 20000));
		employees.put(1079, new Employee("William", "Moses", departments.get(4), jobs.get(18), 59000));
		employees.put(1080, new Employee("Nola", "Mullins", departments.get(9), jobs.get(12), 37000));
		employees.put(1081, new Employee("Charley", "Nelson", departments.get(8), jobs.get(6), 32000));
		employees.put(1082, new Employee("John", "Newbie", departments.get(1), jobs.get(4), 50000));
		employees.put(1083, new Employee("Korey", "Newton", departments.get(9), jobs.get(13), 63000));
		employees.put(1084, new Employee("John", "Niece", departments.get(1), jobs.get(9), 20000));
		employees.put(1085, new Employee("Ken", "Nixon", departments.get(6), jobs.get(20), 62000));
		employees.put(1086, new Employee("John", "Pantry", departments.get(1), jobs.get(9), 20000));
		employees.put(1087, new Employee("John", "Parakeet", departments.get(1), jobs.get(9), 20000));
		employees.put(1088, new Employee("Juliet", "Parker", departments.get(2), jobs.get(6), 45000));
		employees.put(1089, new Employee("Emma", "Parks", departments.get(3), jobs.get(4), 63000));
		employees.put(1090, new Employee("Effie", "Patterson", departments.get(7), jobs.get(7), 20000));
		employees.put(1091, new Employee("Georgette", "Petty", departments.get(6), jobs.get(21), 52000));
		employees.put(1092, new Employee("John", "Physh", departments.get(1), jobs.get(4), 50000));
		employees.put(1093, new Employee("Samuel", "Pugh", departments.get(3), jobs.get(18), 45000));
		employees.put(1094, new Employee("Mary", "Randolph", departments.get(9), jobs.get(4), 62000));
		employees.put(1095, new Employee("Olga", "Ratliff", departments.get(4), jobs.get(18), 45000));
		employees.put(1096, new Employee("John", "Reaper", departments.get(1), jobs.get(8), 30000));
		employees.put(1097, new Employee("Mara", "Reese", departments.get(6), jobs.get(21), 49000));
		employees.put(1098, new Employee("John", "Rite", departments.get(1), jobs.get(9), 20000));
		employees.put(1099, new Employee("Shad", "Robertson", departments.get(4), jobs.get(19), 32000));
		employees.put(1100, new Employee("Aurelia", "Rollins", departments.get(8), jobs.get(3), 80000));
		employees.put(1101, new Employee("Adan", "Roth", departments.get(9), jobs.get(29), 82000));
		employees.put(1102, new Employee("David", "Santana", departments.get(2), jobs.get(26), 19000));
		employees.put(1103, new Employee("John", "Shariff", departments.get(1), jobs.get(9), 20000));
		employees.put(1104, new Employee("Monica", "Shepherd", departments.get(6), jobs.get(23), 33000));
		employees.put(1105, new Employee("Mable", "Skinner", departments.get(2), jobs.get(26), 20000));
		employees.put(1106, new Employee("Roman", "Small", departments.get(6), jobs.get(21), 42000));
		employees.put(1107, new Employee("John", "Smith", departments.get(1), jobs.get(10), 15000));
		employees.put(1108, new Employee("Katelyn", "Smith", departments.get(3), jobs.get(18), 49000));
		employees.put(1109, new Employee("Jerry", "Smyth", departments.get(7), jobs.get(7), 31000));
		employees.put(1110, new Employee("Devin", "Smythe", departments.get(7), jobs.get(5), 64000));
		employees.put(1111, new Employee("Nell", "Stafford", departments.get(5), jobs.get(24), 35000));
		employees.put(1112, new Employee("Arlen", "Stevens", departments.get(3), jobs.get(15), 45000));
		employees.put(1113, new Employee("Ken", "Stevenson", departments.get(3), jobs.get(3), 60000));
		employees.put(1114, new Employee("Audra", "Swanson", departments.get(3), jobs.get(28), 72000));
		employees.put(1115, new Employee("Earlene", "Taylor", departments.get(2), jobs.get(25), 18000));
		employees.put(1116, new Employee("John", "Temple", departments.get(1), jobs.get(9), 20000));
		employees.put(1117, new Employee("Leann", "Valenzuela", departments.get(3), jobs.get(18), 42000));
		employees.put(1118, new Employee("Dora", "Velez", departments.get(2), jobs.get(6), 42000));
		employees.put(1119, new Employee("Renato", "Walsh", departments.get(5), jobs.get(7), 23000));
		employees.put(1120, new Employee("Christian", "Walton", departments.get(4), jobs.get(3), 80000));
		employees.put(1121, new Employee("Lyman", "Ward", departments.get(4), jobs.get(4), 52000));
		employees.put(1122, new Employee("Leo", "Watts", departments.get(8), jobs.get(6), 31000));
		employees.put(1123, new Employee("John", "Wharf", departments.get(1), jobs.get(9), 20000));
		employees.put(1124, new Employee("Lorena", "Wilcox", departments.get(5), jobs.get(32), 60000));
		employees.put(1125, new Employee("Sabrina", "Wiley", departments.get(6), jobs.get(23), 27000));
		employees.put(1126, new Employee("Amie", "Wilkerson", departments.get(3), jobs.get(17), 72000));
		employees.put(1127, new Employee("Rocco", "Williamson", departments.get(3), jobs.get(19), 30000));
		employees.put(1128, new Employee("John", "Wolf", departments.get(1), jobs.get(4), 50000));
		employees.put(1129, new Employee("Lynne", "Workman", departments.get(3), jobs.get(16), 78000));
		employees.put(1130, new Employee("John", "Would", departments.get(1), jobs.get(9), 20000));
		employees.put(1131, new Employee("Antwan", "Wright", departments.get(6), jobs.get(23), 26000));
		employees.put(1132, new Employee("Carmela", "Yang", departments.get(6), jobs.get(3), 75000));
		employees.put(1133, new Employee("John", "Yo", departments.get(1), jobs.get(9),20000));
		employees.put(1134, new Employee("Hubert", "Young", departments.get(5), jobs.get(24), 50000));
		employees.put(1135, new Employee("Rebecca", "Zimmerman", departments.get(2), jobs.get(1), 97000));
	
		for (Employee employee : employees.values()) {
			employee.getDepartment().getEmployees().add(employee);
		}
	}
}
