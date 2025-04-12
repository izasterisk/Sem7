package vn.edu.fpt.jpos.repositories.entities.transaction;

import vn.edu.fpt.jpos.repositories.entities.IError;

public class TransactionERROR extends IError {

    public TransactionERROR(String message) {
        super(message);
    }
}
