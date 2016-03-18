package pl.januszemotoryzacji.service.scheduler.task

import groovy.transform.CompileStatic
import lombok.AllArgsConstructor

@CompileStatic
@AllArgsConstructor
class FetchOfferDataTask implements Runnable {

    private final List<Long> identifiers

    @Override
    void run() {

    }
}
