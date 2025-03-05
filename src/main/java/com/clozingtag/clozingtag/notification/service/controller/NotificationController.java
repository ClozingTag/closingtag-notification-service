package com.clozingtag.clozingtag.notification.service.controller;


import com.clozingtag.clozingtag.notification.service.dto.NotificationRequest;
import com.clozingtag.clozingtag.notification.service.dto.NotificationResponse;
import com.clozingtag.clozingtag.notification.service.service.NotificationService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Hidden
    @PostMapping()
    public ResponseEntity<Void> createNotification(@RequestBody NotificationRequest request) {
        notificationService.createNotification(request);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "get notification by Id",
            description = "Returns notification ",
            tags = {"Notifications"})
    @GetMapping(value = "{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getNotificationId(id));
    }

    @Operation(
            summary = "delete Notification",
            description = "delete notifications",
            tags = {"Notifications"})
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "get Notifications",
            description = "return notifications",
            tags = {"Notifications"})
    @GetMapping(value = "")
    public ResponseEntity<List<NotificationResponse>> getNotifications(
            @Parameter(description = "Page number (default: 0)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page number (default: 0)") @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(notificationService.getNotifications(PageRequest.of(page, size)));
    }

}
