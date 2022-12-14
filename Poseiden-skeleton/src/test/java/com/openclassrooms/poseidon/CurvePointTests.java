package com.openclassrooms.poseidon;

import com.openclassrooms.poseidon.models.CurvePointModel;
import com.openclassrooms.poseidon.repositories.CurvePointRepository;
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
public class CurvePointTests {

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Test
	public void curvePointTest() {
		CurvePointModel curvePoint = new CurvePointModel(10, 10d, 30d);

		// Save
		curvePoint = curvePointRepository.save(curvePoint);
		Assert.assertNotNull(curvePoint.getId());
		Assert.assertEquals(10, (int) curvePoint.getCurveId());

		// Update
		curvePoint.setCurveId(20);
		curvePoint = curvePointRepository.save(curvePoint);
		Assert.assertEquals(20, (int) curvePoint.getCurveId());

		// Find
		List<CurvePointModel> listResult = curvePointRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = curvePoint.getId();
		curvePointRepository.delete(curvePoint);
		Optional<CurvePointModel> curvePointList = curvePointRepository.findById(id);
		Assert.assertFalse(curvePointList.isPresent());
	}

}
