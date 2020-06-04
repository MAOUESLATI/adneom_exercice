package com.partitionlist;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.partitionlist.exception.TailleListException;

@RunWith(MockitoJUnitRunner.class)
public class PartitionListeTest {

	@InjectMocks
	private PartitionListe partitionListe;

	@Test
	public void resultatTroisSousList() throws TailleListException {
		List<Integer> integerList = Arrays.asList(0, 2, -2, 13, 54, 25, 46, 7);
		Collection<List<Integer>> result = partitionListe.partiton(integerList, 3);
		assertThat(result.size(), is(3));
	}

	@Test
	public void resultatUneListeAvecTailleMax() throws TailleListException {

		List<Integer> liste = Arrays.asList(1, 2, 3, 4, 5);
		Collection<List<Integer>> result = partitionListe.partiton(liste, Integer.MAX_VALUE);
		assertThat(result.size(), is(1));
	}

	@Test
	public void resultatDeuxListeChaine() throws TailleListException {
		List<String> ChaineListe = Arrays.asList("A", "B", "C", "D");
		Collection<List<String>> resultat = partitionListe.partiton(ChaineListe, 2);
		assertThat(resultat.size(), is(2));
	}

	@Test
	public void resultatDeuxListeObjet() throws TailleListException {
		List<Object> objetListe = Arrays.asList(new Integer("1"), new String(), Boolean.valueOf(true));
		Collection<List<Object>> resultat = partitionListe.partiton(objetListe, 2);
	}

	@Test
	public void resultatListeVideDeListeVide() throws TailleListException {
		List<String> listeVide = Collections.emptyList();
		Collection<List<String>> resultat = partitionListe.partiton(listeVide, 2);
		assertThat(resultat.size(), is(0));

	}

	@Test
	public void resultatListeVideDeListeNulle() throws TailleListException {
		Collection<List<Object>> resultat = partitionListe.partiton(null, 1);
		assertEquals(Collections.emptyList(), Collections.emptyList());
	}

	@Test
	public void resultatExceptionSiTailleNeggative() {
		List<Integer> liste = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		try {
			Collection<List<Integer>> resultat = partitionListe.partiton(liste, -2);
		} catch (TailleListException e) {
			assertThat(e.getMessage(), is("Negative size is not valid"));
		}

	}

	@Test
	public void resultatExceptionSiTailleZero() {
		List<Integer> liste = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		try {
			Collection<List<Integer>> resultat = partitionListe.partiton(liste, 0);
		} catch (TailleListException e) {
			assertThat(e.getMessage(), is("Zero is not a valid size"));
		}
	}

}