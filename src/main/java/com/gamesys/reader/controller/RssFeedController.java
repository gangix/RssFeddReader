package com.gamesys.reader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamesys.reader.model.Entry;
import com.gamesys.reader.service.EntryService;

@RestController
public class RssFeedController {

	@Autowired
	private EntryService entryService;

	@GetMapping("/api/entries")
	public ResponseEntity<List<Entry>> getEntries() throws Exception {
		return ResponseEntity.ok(entryService.getLastEntries());
	}

}