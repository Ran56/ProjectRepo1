package com.infinity.gamesFactory.service;


import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private Logger logger = LoggerFactory.getLogger(getClass());


    private AmazonSQS sqsClient;


    private String queueName = System.getProperty("queueName");

    private String queueUrl;

    public MessageService(@Autowired AmazonSQS sqsClient)
    {
        this.sqsClient = sqsClient;
        logger.info("sqsclient"+sqsClient.toString());
        logger.info("sqsclient"+queueName);
        this.queueUrl = getQueueUrl(queueName);

    }

    public void sendMessage(String messageBody, Integer delaySec)
    {
        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(messageBody)
                .withDelaySeconds(delaySec);
        sqsClient.sendMessage(sendMessageRequest);
    }

    public String getQueueUrl(String queueName) {

        GetQueueUrlResult getQueueUrlResult = sqsClient.getQueueUrl(queueName);
        //logger.info("QueueUrl: " + getQueueUrlResult.getQueueUrl());
        return getQueueUrlResult.getQueueUrl();
    }

}
