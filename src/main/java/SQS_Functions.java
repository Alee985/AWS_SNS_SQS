import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;
import com.amazonaws.services.lambda.model.*;
import java.util.List;

public class SQS_Functions {
    static AmazonSQS sqs= AmazonSQSClientBuilder.standard().build();
    static String queueUrl="http://localhost:4566/000000000000/queue1";

    public static void PublishTopic() {
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", "us-west-2"))
                .build();
        final String msg = "Hey, this is second check message, Saad Ali!!";
        final PublishRequest publishRequest = new PublishRequest("arn:aws:sns:us-east-1:000000000000:topic1", msg);
        final PublishResult publishResponse = snsClient.publish((publishRequest));

// Print the MessageId of the message.
        System.out.println("Your message has been published to the given topic :)");
        System.out.println("MessageId: " + publishResponse.getMessageId());
    }

    public static void SendMessage(){
        try {
            SendMessageRequest send_msg_req = new SendMessageRequest().withQueueUrl(queueUrl)
                    .withMessageBody("Welcome from SQS- Hello Message!")
                    .withDelaySeconds(0);
            SendMessageResult send_msg_rslt = sqs.sendMessage(send_msg_req);
            System.out.println("Message Sent Successfully!");
        }
        catch(Exception e) {
            System.out.println("Failed");
        }

    }

    public static void ReadMessage(){
        System.out.println("Reading Messages From Queue");
        ReceiveMessageRequest req=new ReceiveMessageRequest().withQueueUrl(queueUrl).withVisibilityTimeout(0).withWaitTimeSeconds(10).withMaxNumberOfMessages(10);
        List<Message> messages=sqs.receiveMessage(req).getMessages();
        int i=0;
        for(Message m:messages){
            System.out.println(m.toString());
            i++;
        }
        System.out.println("Count: " + i);
        System.out.println("Queue Scanned Successfully!");
    }

    public static void DeleteMessage(){
        ReceiveMessageRequest req=new ReceiveMessageRequest().withQueueUrl(queueUrl).withVisibilityTimeout(20).withMaxNumberOfMessages(1);
        List<Message> messages=sqs.receiveMessage(req).getMessages();
        for (Message m : messages){
            System.out.println("Deleting Message!");
            DeleteMessageResult res= sqs.deleteMessage(queueUrl,m.getReceiptHandle());
            System.out.println(res.getSdkHttpMetadata());
            System.out.println("Message Deleted Successfully!");
        }
    }

    public static void main(String[] args) {

        SendMessage();
        ReadMessage();
        //DeleteMessage();
         PublishTopic();
    }
}