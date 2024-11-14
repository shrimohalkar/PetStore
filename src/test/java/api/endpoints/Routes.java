package api.endpoints;

	/*
	 * Swagger URI --> https://petstore.swagger.io
	 * 
	 * #Create user(Post) : https://petstore.swagger.io/v2/user #Get user
	 * https://petstore.swagger.io/v2/user/{username} #Update user
	 * https://petstore.swagger.io/v2/user/{username} #Delete user
	 * https://petstore.swagger.io/v2/user/{username}
	 * 
	 * #User Model post_url=https://petstore.swagger.io/v2/user
	 * get_url=https://petstore.swagger.io/v2/user/{username}
	 * update_url=https://petstore.swagger.io/v2/user/{username}
	 * delete_url=https://petstore.swagger.io/v2/user/{username}
	 * 
	 * #Store Model 
	 * #Here you can add Store url's
	 * 
	 * #Pet Model 
	 * #Here you can add Pet url's
	 * 
	 */

public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//User Module
	
	public static String post_url=base_url+"/user";
    public static String get_url=base_url+"/user/{username}";
    public static String update_url=base_url+"/user/{username}";
    public static String delete_url=base_url+"/user/{username}";
    
    // Pet Module
    
    	//https://petstore.swagger.io/v2/pet
    
    // Store Module

}
