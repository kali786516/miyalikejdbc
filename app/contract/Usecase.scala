package contract

import scala.concurrent.{ExecutionContext, Future}

/**
  * ・レイヤードアーキテクチャでいうアプリケーション(サービス)層
  * ・アプリケーション固有のビジネスルールをカプセル化
  *   ・システムのユースケースを実装する。ドメインへのデータの流れを調整し、また、ドメインオブジェクトへ指示を出す
  *   ・この層の変更がドメイン層に影響したり、DBやUIのような外部の変更によって影響を受けない
  *   ・アプリケーション操作に変更がある場合、この層は影響を受ける
  */
trait Usecase {
  type In

  type Out

  protected def call(arg: In)(implicit ec: ExecutionContext): Future[Out]
}
