import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

import java.util.List;

public class Send {


    public static void main(String[] args) {
        AmazonSQS sqs= AmazonSQSClientBuilder.standard().build();
        String queueUrl="http://localhost:4566/000000000000/onexlab";
        SendMessageRequest send_msg_req=new SendMessageRequest().withQueueUrl(queueUrl)
                .withMessageBody("hello All")
                .withDelaySeconds(10);
        int i=0;
        while(i<=100) {
           i++;
            SendMessageResult send_msg_rslt = sqs.sendMessage(send_msg_req);
            System.out.println(send_msg_rslt.getMessageId());
        }
    }
}
