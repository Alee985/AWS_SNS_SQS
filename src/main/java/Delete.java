import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

public class Delete {
    public static void main(String[] args) {
        AmazonSQS sqs= AmazonSQSClientBuilder.standard().build();
        String queueUrl="http://localhost:4566/000000000000/onexlab";
        ReceiveMessageRequest req=new ReceiveMessageRequest().withQueueUrl(queueUrl).withVisibilityTimeout(20).withMaxNumberOfMessages(10);
        List<Message> messages=sqs.receiveMessage(req).getMessages();
        for (Message m : messages){
            System.out.println("Deleting.....");
            DeleteMessageResult res= sqs.deleteMessage(queueUrl,m.getReceiptHandle());
            System.out.println(res.getSdkHttpMetadata());
        }
    }
}
