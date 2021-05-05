package org.tnmk.practicetwiliofullflow.pro01besimple.datetime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateTimeRepository extends JpaRepository<DateTimeEntity, Integer> {
}
