package com.amaury.chatdoc.service

interface EmailSenderService {
    void sendEmail(String to, String body)
}