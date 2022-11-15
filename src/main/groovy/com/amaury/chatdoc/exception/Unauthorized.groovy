package com.amaury.chatdoc.exception

class Unauthorized extends RuntimeException {
    static final long serialVersionUID = 401L

    Unauthorized(String message) {
        super(message)
    }
}
