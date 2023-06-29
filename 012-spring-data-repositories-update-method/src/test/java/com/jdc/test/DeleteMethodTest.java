package com.jdc.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.config.JpaConfiguration;
import com.jdc.location.entity.State;
import com.jdc.location.entity.Type;
import com.jdc.location.repo.StateRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
@Sql(scripts = { "/init-table.sql", "/load-data.sql" })
public class DeleteMethodTest {

	@Autowired
	private StateRepo repo;

	@Disabled
	@ParameterizedTest
	@CsvSource(value = { "1	Ayeyarwady	Region	South	Pathein	6184829	14" }, delimiter = '\t')
	void test_delete_by_entity(int id, String name, Type type, String region, String capital, int population,
			long remains) {
		// Create Entity Object
		var input = new State(id, name, type, region, capital, population);

		// Execute Delete Method
		repo.delete(input);

		// Check Result
//		assertEquals(14, repo.count());
		assertThat(repo.count(), is(remains));
	}

	@Disabled
	@ParameterizedTest
	@CsvSource({ "1,14" })
	void test_delete_by_id(int id, long remains) {
		repo.deleteById(id);

		assertThat(repo.count(), is(remains));
	}

	@Disabled
	@Test
	void test_delete_all() {
		repo.deleteAll();

		assertThat(repo.count(), is(0L));
	}

	@Disabled
	@Test
	void test_delete_all_in_batch() {
		repo.deleteAllInBatch();

		assertThat(repo.count(), is(0L));
	}

	@Disabled
	@ParameterizedTest
	@CsvSource(delimiter = '\t', value = {
			"1,5,3	12"
	})
	void test_delete_all_by_ids(String ids, long remain) {
		var array=ids.split(",");
//		var idList=Arrays.stream(array).map(a->Integer.parseInt(a)).toList();
		var idList=Arrays.stream(array).map(Integer::parseInt).toList();
		repo.deleteAllById(idList);

		assertThat(repo.count(), is(remain));
	}
	
	@Disabled
	@ParameterizedTest
	@CsvSource(delimiter = '\t', value = {
			"1,5,3	12"
	})
	void test_delete_all_by_ids_in_batch(String ids, long remain) {
		var array=ids.split(",");
//		var idList=Arrays.stream(array).map(a->Integer.parseInt(a)).toList();
		var idList=Arrays.stream(array).map(Integer::parseInt).toList();
		repo.deleteAllByIdInBatch(idList);

		assertThat(repo.count(), is(remain));
	}
//	==========================================================
//	==========================================================
	
	static Stream<Arguments> idsForDelete(){
		return Stream.of(
				Arguments.of(List.of(1,2,3),12),
				Arguments.of(List.of(1,2,3,4,5),10)
				);
	}
	
	@ParameterizedTest
	@MethodSource("idsForDelete")
	void test_delete_all_by_ids(List<Integer> ids,long remains) {
		repo.deleteAllById(ids);
		assertThat(repo.count(),is(remains));
	}
	
	@ParameterizedTest
	@MethodSource("idsForDelete")
	void test_delete_all_by_ids_in_batch(List<Integer> ids,long remains) {
		repo.deleteAllByIdInBatch(ids);
		assertThat(repo.count(), is(remains));
	}
	
//	===========================================
//	===========================================
	
	static Stream<Arguments> entitiesForDelete(){
		return Stream.of(
					Arguments.of(
							List.of(
									new State(1,"Ayeyarwady", Type.Region, "Lower", "Pathein", 6184829)
							),14),
							Arguments.of(
									List.of(
											new State(1,"Ayeyarwady", Type.Region, "Lower", "Pathein", 6184829),
											new State(2,"Bago", Type.Region, "Lower", "Bago", 4867373)
									),13
						)
				);
	}
	
	
	@ParameterizedTest
	@MethodSource("entitiesForDelete")
	void test_delete_all_by_entities_in_batch(List<State> states, long remain) {
		repo.deleteAllInBatch(states);
		assertThat(repo.count(), is(remain));
	}
	
	@ParameterizedTest
	@MethodSource("entitiesForDelete")
	void delete_all_by_entities(List<State> states,long remains) {
		repo.deleteAll(states);
		assertThat(repo.count(), is(remains));
	}
}
