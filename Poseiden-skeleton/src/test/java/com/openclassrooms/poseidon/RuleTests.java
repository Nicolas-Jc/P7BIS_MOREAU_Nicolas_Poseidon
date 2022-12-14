package com.openclassrooms.poseidon;

import com.openclassrooms.poseidon.models.RuleNameModel;
import com.openclassrooms.poseidon.repositories.RuleNameRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleTests {

	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Test
	public void ruleTest() {
		RuleNameModel rule = new RuleNameModel("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

		// Save
		rule = ruleNameRepository.save(rule);
		Assert.assertNotNull(rule.getId());
		Assert.assertEquals("Rule Name", rule.getName());

		// Update
		rule.setName("Rule Name Update");
		rule = ruleNameRepository.save(rule);
		Assert.assertEquals("Rule Name Update", rule.getName());

		// Find
		List<RuleNameModel> listResult = ruleNameRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = rule.getId();
		ruleNameRepository.delete(rule);
		Optional<RuleNameModel> ruleList = ruleNameRepository.findById(id);
		Assert.assertFalse(ruleList.isPresent());
	}
}
