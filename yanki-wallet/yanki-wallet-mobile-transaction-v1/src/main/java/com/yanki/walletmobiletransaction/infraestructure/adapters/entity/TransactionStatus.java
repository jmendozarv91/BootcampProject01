package com.yanki.walletmobiletransaction.infraestructure.adapters.entity;

public enum TransactionStatus {
  /**
   * La transacción ha sido creada pero aún no se ha procesado.
   */
  CREATED,

  /**
   * La transacción se ha procesado correctamente.
   */
  SUCCESS,

  /**La transacción se ha cancelado.
   */
  CANCELED,

  /**
   *
   * La transacción ha fallado.
   */
  FAILED
}
