package com.gamesys.reader.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamesys.reader.model.Entry;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {
	 List<Entry> findTop10ByOrderByTransactionDateTimeDescPublishDateTimeDesc();
}
