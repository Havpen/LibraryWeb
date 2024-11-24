package com.alisievich.Issue.repository;

import com.alisievich.Issue.model.Issue;
import org.springframework.data.repository.CrudRepository;

public interface IssueRepository extends CrudRepository<Issue, Integer> {
}
