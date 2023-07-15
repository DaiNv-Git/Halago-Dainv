package com.example.halagodainv.controller;

import com.example.halagodainv.request.group.GroupAddRequest;
import com.example.halagodainv.request.group.GroupEditRequest;
import com.example.halagodainv.request.group.GroupSearch;
import com.example.halagodainv.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("")
    public ResponseEntity<Object> getALl(@RequestBody GroupSearch groupSearch) {
        return ResponseEntity.ok(groupService.getGroupAll(groupSearch));
    }

    @GetMapping("/detail")
    public ResponseEntity<Object> getDetail(@RequestParam("groupId") long groupId) {
        return ResponseEntity.ok(groupService.detail(groupId));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> insert(@RequestBody GroupAddRequest groupAddRequest) {
        return ResponseEntity.ok(groupService.insert(groupAddRequest));
    }

    @PostMapping("/edit")
    public ResponseEntity<Object> update(@RequestBody GroupEditRequest groupEditRequest) {
        return ResponseEntity.ok(groupService.edit(groupEditRequest));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam("groupId") long groupId) {
        return ResponseEntity.ok(groupService.delete(groupId));
    }
}
