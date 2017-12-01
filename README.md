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

The following example uses two collections a publisher and a book. A publisher has many books, the way it is utilized here is by aggregating ObjectId's into an array. The downside to this is that it is an array without any bounds.

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
To make the best decision in designing a scheme in MongoDB, [William Zola, Lead Technical Support Engineer at MongoDB](https://www.mongodb.com/blog/post/6-rules-of-thumb-for-mongodb-schema-design-part-3) has a few rules of thumb.

1. Favor embedding unless there is a compelling reason not to
2. Needing to access an object on its own is a compelling reason not to embed it
3. Arrays should not grow without bound. If there are more than a couple of hundred documents on the “many” side, don’t embed them; if there are more than a few thousand documents on the “many” side, don’t use an array of ObjectID references. High-cardinality arrays are a compelling reason not to embed.
4. Don’t be afraid of application-level joins: if you index correctly and use the projection specifier (query that only returns the field specified and not the whole document) then application-level joins are barely more expensive than server-side joins in a relational database.
5. Consider the write/read ratio when denormalizing. A field that will mostly be read and only seldom updated is a good candidate for denormalization: if you denormalize a field that is updated frequently then the extra work of finding and updating all the instances is likely to overwhelm the savings that you get from denormalizing.
6. As always with MongoDB, how you model your data depends – entirely – on your particular application’s data access patterns. You want to structure your data to match the ways that your application queries and updates it.

The main choices are as followed:

- For “one-to-few”, you can use an array of embedded documents
- For “one-to-many”, or on occasions when the “N” side must stand alone, you should use an array of references. You can also use a “parent-reference” on the “N” side if it optimizes your data access pattern.
- For “one-to-squillions”, you should use a “parent-reference” in the document storing the “N” side.

Once you’ve decided on the overall structure of the data, then you can, if you choose, denormalize data across multiple documents, by either denormalizing data from the “One” side into the “N” side, or from the “N” side into the “One” side. You’d do this only for fields that are frequently read, get read much more often than they get updated, and where you don’t require strong consistency, since updating a denormalized value is slower, more expensive, and is not atomic.

## Indexes



# Setting up a MongoDB connection

# MongoDB Driver vs Frameworks
## MongoDB Driver
## Morphia (from MongoDB)
## Spring Mongo
