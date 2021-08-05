import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

public class Read {
    public static void main(String[] args) {
        AmazonSQS sqs= AmazonSQSClientBuilder.standard().build();
        String queueUrl="http://localhost:4566/000000000000/onexlab";

        System.out.println("\nReceiving Message............................................................... ");
        ReceiveMessageRequest req=new ReceiveMessageRequest().withQueueUrl(queueUrl).withVisibilityTimeout(10).withWaitTimeSeconds(10).withMaxNumberOfMessages(10);
        List<Message> messages=sqs.receiveMessage(req).getMessages();

        for(Message m:messages){
            System.out.println(m.toString());
           /* System.out.println("Deleting.....");
            sqs.deleteMessage(queueUrl,m.getReceiptHandle());*/
        }
    }

}
