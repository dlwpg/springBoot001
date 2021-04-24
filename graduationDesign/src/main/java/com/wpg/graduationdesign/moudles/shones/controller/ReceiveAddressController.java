package com.wpg.graduationdesign.moudles.shones.controller;

import com.wpg.graduationdesign.moudles.shones.service.ReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wpg
 */
@RestController
public class ReceiveAddressController {
    @Autowired
    private ReceiveAddressService receiveAddressService;
}
