package com.partitionlist;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.partitionlist.exception.TailleListException;

public class PartitionListe {

	/**
	 *
	 * @param liste
	 * @param size
	 * @return Collection of list
	 * @throws TailleListException 
	 */
	public <T> Collection<List<T>> partiton(List<T> liste, int taille) throws TailleListException {

		if (liste == null) {
			return Collections.emptyList();
		}
		if (taille < 0) {
			throw new TailleListException("Negative size is not valid");
		}
		final AtomicInteger count = new AtomicInteger();
		try {
			Collection<List<T>> resultat = liste.stream()
					.collect(Collectors.groupingBy(item -> count.getAndIncrement() / taille)).values();
			return resultat;
		} catch (ArithmeticException e) {
			throw new TailleListException("Zero is not a valid size");
		}

	}
}
