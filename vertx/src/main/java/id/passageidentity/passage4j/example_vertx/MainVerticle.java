package id.passageidentity.passage4j.example_vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    DeploymentOptions optsWorker = new DeploymentOptions().setWorker(true).setWorkerPoolSize(20);

    vertx.deployVerticle(new WebVerticle(), optsWorker).onComplete(
      ar -> {
      if (ar.failed()) {
        startPromise.fail(ar.cause());
      } else {
        startPromise.complete();
      }});

  }
}
