package com.ea.dataone.repository;

import com.ea.dataone.entity.ActivityLog;
import org.springframework.data.repository.ListCrudRepository;

public interface ActivityLogRepository extends ListCrudRepository<ActivityLog, Long> {
}
