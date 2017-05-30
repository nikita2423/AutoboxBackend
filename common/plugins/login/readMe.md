# login plugin for Snaphy


###Plugin for adding login functionality

###This plugin is exposed on  `/login` route


1) Login Plugin accept a `User` model which act as a base database for managing admin user login
2) This plugin also create a static role `$admin` for `User` model which act as an admin user from rest users. 
   You can assign admin static role by  adding `adminUser` property on login plugin conf.json file.  

This plugin exposes several internal and external methods for uses.
####External Methods (REST) 
1) Method =>`verifyRole` Type => `Post` Accepts=> {role:  string} Return {isInRole: boolean} Model => `Role`  
   Example  
   ```
     //Test if the given role is applicable to the current loggedin user..
     Role.verifyRole('admin', function(success){}, function(error){});
   ```

2) Method =>`isAdmin` Type => `Post` Accepts=> {} Return {isAdmin: boolean} Model => `User`  
   Example  
   ```
     //Test if the given role is admin user.
     Employee.isAdmin('admin', function(success){}, function(error){})
     
   ```

####INTERNAL Methods  
 How to use internal methods in another plugin?  
 ```
  //Import the login plugin methods using helper method first. 
  const login = helper.loadPlugin('login');
  //Check if the current user is admin user or not..
  /**
  *@param app {loopback server object}
  *@param currentContext {object} loopback logged user context.  var currentContext = loopback.getCurrentContext();
  *@param cb {function(err, isAdmin)} Callback function
  **/
  login.isAdmin(app, currentContext, cb)
  
  /**
   * Internal method for checking if current user in a role with the given loopback..
   * Method to be useful fot plugins..
   * @param role {string} role name
   * @param callback {function(err, isInRole)}
   */
  login.verifyRole(role, callback); 
 ```
 
 
 ####Warning: When creating an `static Role` always add role `name` and role id as same or else loopback wont be able to search. As loopback loopback search role by id.  
 ####Example  
 ```
 //create the admin role
             Role.findOrCreate(
             {
                 where:{
                     id: '$admin',
                     name: '$admin'
                 }
             },
             {
                 id: '$admin',
                 name: '$admin'
             }, function(err, role) {
                 if (err){
                     //role already exists..
                     Role.find({
                         id: "$admin"
                         name: "$admin"
                     }, function(err, roleList){
                         if(roleList){
                             if(roleList.length){
                                 const role = roleList[0];
                                 for (i = 0; i < users.length; i++) {
                                     //Making this user an admin.
                                     addUserAdmin(role, users[i].id);
                                 } //for loop..
                             }
                         }
 
                     });
                 }else{
                     for (i = 0; i < users.length; i++) {
                         //Making this user an admin.
                         addUserAdmin(role, users[i].id);
                     } //for loop..
                 }
 
             });
 ```
 
 




#####Written by Robins Gupta

