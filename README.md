# Friend-recommendation-using-movie-data

This project is composed of two phases-
1) Querying of the movie dataset for simple statistics retrieval.
2) Friend recommendation based on user input and the dataset of users and their choices.

There are two datasets - ip.txt(User ratings dataset) and impfields.txt(Movie dataset)

The friend recommendation consists of two mappers and two reducers.
Mapper1 converts the dataset into key, value pairs of user_id, movie_id
Reducer1 combines all the movies watched by a user
Mapper2 will just pass the output of Reducer1 in the form of user_id, (movie_id1, movie_id2,...)
Reducer2 will find the similarity between the mapper output and the data (movies liked) given by the user.
