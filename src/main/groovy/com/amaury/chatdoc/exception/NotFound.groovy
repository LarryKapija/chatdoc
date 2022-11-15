package com.amaury.chatdoc.exception

class NotFound extends RuntimeException {
    static final long serialVersionUID = 404L

    NotFound(String message) {
        super(message)
    }
}
