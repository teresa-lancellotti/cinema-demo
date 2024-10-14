package com.example.springboot.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.example.springboot.model.Outcome;
import com.example.springboot.model.OutcomeCode;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UtilityFactory {

	public static Outcome createOutcome(OutcomeCode code, String messagge) {
		Outcome outcome = new Outcome();
		outcome.setCode(code);
		outcome.setDescription(messagge);
		return outcome;
	}


	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) 
	{
	    Map<Object, Boolean> map = new ConcurrentHashMap<>();
	    return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
}
