##### 1. Create SSH key
 ```shell script
$ ssh-keygen -t rsa
```

##### 2. Add SSH key to Heroku
 ```shell script
$ heroku keys:add
```
 
 ##### 3. Add SSH Key to TeamCity
 ```
 Go to Project --> <Project_Name> --> Edit Project --> SSH Keys
```
 ##### 4. Add an SSH agent to the build configuration
 ```
 Go to <Build Configuration> --> Build Features --> Add build feature
```
 ##### 5. Add a Build Step to the build configuration
 
 ###### Gradle
    * Type: Gradle
    * Gradle Task: clean build
  
    This step will compile and build the project
  
 ##### 6. Run the build configuration manually 
 
 ```
Click on Run. (LOL)
```
 
 ##### 7. Add git repository created by TeamCity to Heroku

 When TeamCity run a build configuration, it clone the CSV repository to a folder. We need to locate this local git 
repository and add it to Heroku. By default the directory of clone projects by TeamCity is located at `/opt/TeamCity/buildAgent/work/`

You can find the name of the git repository in the log of the last run. Change the current working directory to that folder and execute:
```shell script
$ sudo heroku git:remote -a siqpik
```

 ##### 8. Add a Build Step to the build configuration
###### Push to Heroku
     * Type: Command Line
     * Custom script: git push heroku develop:master
     * Execute step: If all previous steps finished successfully
     
     This step will push the develop branch of our project to Heroku's project master branch.
     Heroku will deploy our app automatically when we push to master branch
 
 
 
 
 ### Done!

 