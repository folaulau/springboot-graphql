import React from "react";
import { useEffect} from "react";
import { GraphQLWsLink } from '@apollo/client/link/subscriptions';
import { createClient } from 'graphql-ws';
import { ApolloClient, InMemoryCache, useSubscription, ApolloProvider } from '@apollo/client';
import { gql } from "@apollo/client";

const wsLink = new GraphQLWsLink(createClient({
  url: 'ws://127.0.0.1:8081/graphql',
}));

const client = new ApolloClient({
  link: wsLink,
  cache: new InMemoryCache()
});

const COMMENTS_SUBSCRIPTION = gql`
  subscription{
    getMessagesForChat{
      id
      message
      user{
        id
        firstName
      }
    }
  }
`;

function LatestComment() {
  const { data, loading, error } = useSubscription(
    COMMENTS_SUBSCRIPTION
  );
  console.log("data")
  console.log(data)
  console.log("loading")
  console.log(loading)
  console.log("error")
  console.log(error)
  return <h4>New comment: {!loading && data}</h4>;
}

function App() {



  useEffect(() => {



    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);



  return (
    <ApolloProvider client={client} >
    <div className="container">
      <div className="row">
        <div className="col-6 offset-3">

        <div className="row">
          <div className="col-12">
            <h1>Chat</h1>

            <LatestComment />
           
          </div>
        </div>
        </div>
      </div>
    </div>
    </ApolloProvider>
  );
}

export default App;
