The given code explores a given directory sequentially and finds the total number of files in the directory
and its subdirectories, recursively.

Run the program on a deep directory first, note the time. Run it a few times to get a stable time.

Then use actors to make this code concurrent. 

A master actor can take the directory name and send it off to a Router actor. 
The router actor will send the directory to a explore directory actor. This will send back to
the master any directory it finds and will also send to the master the number of files in the
immediate directory given.

The master will keep track of the count and use the explore directory actors (through the router)
to explore the subdirectories.
