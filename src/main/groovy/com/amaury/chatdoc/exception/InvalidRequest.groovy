package com.amaury.chatdoc.exception

class InvalidRequest extends RuntimeException {
    static final long serialVersionUID = 400L

    InvalidRequest(String message) {
        super(message)
    }
}
