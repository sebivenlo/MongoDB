# mongoDB

To show you the power of mongodb we are going to explain
what mongodb can do in specific use-cases. The first part
will be used to explain how mongo “works” and the second
part will be used for the interactive part. The use-cases are:

- Pros and cons for scheme less databases
- Getting data without complex joins
- Framework for easier database access

# Content Workshop

- Introduction MongoDB
    - Scheme Design
    - Indexes
- Setting up a MongoDB connection
- MongoDB Driver vs Frameworks
    - MongoDB Driver
    - Morphia (from MongoDB)
    - Spring Mongo

# Introduction MongoDB

## Scheme Design
### When to use an embedded document 
### Relations

#### One-to-One
Consider that there are two collections, E.g. person and address. This would be the way to do it in a relational database. In MongoDB the user can embed data into other documents (see example 2).


Example 1:
```
{
   _id: "joe",
   name: "Joe Bookreader"
}

{
   patron_id: "joe",
   street: "123 Fake Street",
   city: "Faketon",
   state: "MA",
   zip: "12345"
}
```

Example 2:
```
{
   _id: "joe",
   name: "Joe Bookreader",
   address: {
              street: "123 Fake Street",
              city: "Faketon",
              state: "MA",
              zip: "12345"
            }
}
```

#### One-to-Many
For this relation the same applies as above. But the difference here is that a person can have multiple addresses. This translates into example 3 when the database is relational.

Example 3:
```
{
   _id: "joe",
   name: "Joe Bookreader"
}

{
   patron_id: "joe",
   street: "123 Fake Street",
   city: "Faketon",
   state: "MA",
   zip: "12345"
}

{
   patron_id: "joe",
   street: "1 Some Other Street",
   city: "Boston",
   state: "MA",
   zip: "12345"
}
```
This design can be optimized for MongoDB, this will improve the read and write speed of our database. To solve this issue the data can be embedded into the the person document. By not having to go into multiple collection the read and write operation is simplified and a lot faster.

```
{
   _id: "joe",
   name: "Joe Bookreader",
   addresses: [
                {
                  street: "123 Fake Street",
                  city: "Faketon",
                  state: "MA",
                  zip: "12345"
                },
                {
                  street: "1 Some Other Street",
                  city: "Boston",
                  state: "MA",
                  zip: "12345"
                }
              ]
 }
```
#### One-to-Many
There are two ways of portraying a one to many relationship in MongoDB. 

1. Put everything into one collection
2. Split it up into two collections. (relational way)

For this example the following types are used: publisher and a book.

This example uses an embedded document to portrait the one to many relationship. The downside to this is update speed. When the publisher changes every document needs to be updated. Use this method only when the data used is rarely updated.

```
{
   title: "MongoDB: The Definitive Guide",
   author: [ "Kristina Chodorow", "Mike Dirolf" ],
   published_date: ISODate("2010-09-24"),
   pages: 216,
   language: "English",
   publisher: {
              name: "O'Reilly Media",
              founded: 1980,
              location: "CA"
            }
}

{
   title: "50 Tips and Tricks for MongoDB Developer",
   author: "Kristina Chodorow",
   published_date: ISODate("2011-05-06"),
   pages: 68,
   language: "English",
   publisher: {
              name: "O'Reilly Media",
              founded: 1980,
              location: "CA"
            }
}
```

The following example uses two collections a publisher and a book. A publisher has many books, the way it utilized here is by aggregating ObjectId's into an array. The downside to this is that there is no reference to the publisher anymore (this would be okay if it was many to many). A better way of solving this is by two way referencing.

```
{
   name: "O'Reilly Media",
   founded: 1980,
   location: "CA",
   books: [123456789, 234567890, ...]
}

{
    _id: 123456789,
    title: "MongoDB: The Definitive Guide",
    author: [ "Kristina Chodorow", "Mike Dirolf" ],
    published_date: ISODate("2010-09-24"),
    pages: 216,
    language: "English"
}

{
   _id: 234567890,
   title: "50 Tips and Tricks for MongoDB Developer",
   author: "Kristina Chodorow",
   published_date: ISODate("2011-05-06"),
   pages: 68,
   language: "English"
}
```

The best solution for this problem would be referencing the publisher in the book by just the ObjectId. There will be some loss in read speed, but it will improve the write speed. 

```
{
   _id: "oreilly",
   name: "O'Reilly Media",
   founded: 1980,
   location: "CA"
}

{
   _id: 123456789,
   title: "MongoDB: The Definitive Guide",
   author: [ "Kristina Chodorow", "Mike Dirolf" ],
   published_date: ISODate("2010-09-24"),
   pages: 216,
   language: "English",
   publisher_id: "oreilly"
}

{
   _id: 234567890,
   title: "50 Tips and Tricks for MongoDB Developer",
   author: "Kristina Chodorow",
   published_date: ISODate("2011-05-06"),
   pages: 68,
   language: "English",
   publisher_id: "oreilly"
}
```

#### Scheme Decisions
--------------------------- recap explain scheme choices ----------------


## Indexes

# Setting up a MongoDB connection

# MongoDB Driver vs Frameworks
## MongoDB Driver
## Morphia (from MongoDB)
## Spring Mongo
