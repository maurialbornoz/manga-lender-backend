package com.maurialbornoz.mangalenderbackend.email;

public interface EmailSender {
    void send(String to, String email);
}
