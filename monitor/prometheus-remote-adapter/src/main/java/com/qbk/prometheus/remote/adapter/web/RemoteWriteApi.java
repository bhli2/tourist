package com.qbk.prometheus.remote.adapter.web;

import com.google.protobuf.InvalidProtocolBufferException;
import com.qbk.prometheus.remote.adapter.service.RemoteWriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class RemoteWriteApi {

  @Autowired
  private RemoteWriteService remoteWriteService;

  @PostMapping("/write")
  public void write(@RequestBody byte[] data) throws InvalidProtocolBufferException {
    log.info(" receive msg from Prometheus ...");
    remoteWriteService.resolveRequest(data);
  }

}
