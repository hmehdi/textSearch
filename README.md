## Command line driven text search engine
 
This project is to write a command line driven text search engine

This project reads all the text files in the given directory, 
building an in memory and then give a command prompt at which interactive searches can be run.

The search engine take the words on the command prompt and return a list of the top 
matching file names in rank order and giving the rank score.

#### Ranking

- The rank score is 100% if a file contains all the words
- It is 0% if it contains none of the words
- It is between 0 and 100 when contains some of the words


#### How to build it 

```
sbt clean compile
```

#### How to run it 

```
sbt run  ${path_to_txt_files}
```

#### How to use it all in one 

From whenever this project has been cloned: 

```
$ sbt clean compile && sbt compile && sbt run  ./src/test/resources/files
10 files read in directory ./src/test/resources/files 

search> to be or not to be
filename1 : 100% 
filename2 : 95% 
search>
search> cats
no matches found 
search> :quit

```