package cjfw.wms.ms.service.district;

import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class ProcessResult {
    public static final Triple<Set<String>, Set<String>, Set<String>> EMPTY_TRIPLE = Triple.of(Collections.emptySet(), Collections.emptySet(), Collections.emptySet());
    public static final Pair<Set<String>, Set<String>> EMPTY_PAIR = Pair.of(Collections.emptySet(), Collections.emptySet());
    private String batchKey;
    private Object insertResult;
    private Object updateResult;
    private Object deleteResult;

    public <E> E getInsertResult() {
        return getAs(insertResult);
    }
    public <E> E getUpdateResult() {
        return getAs(updateResult);
    }
    public <E> E getDeleteResult() {
        return getAs(deleteResult);
    }

    public <E> Set<E> getInsertSetAs() {
        return getSetAs(insertResult);
    }
    public <E> Set<E> getUpdateSetAs() {
        return getSetAs(updateResult);
    }
    public <E> Set<E> getDeleteSetAs() {
        return getSetAs(deleteResult);
    }

    public <E> List<E> getInsertListAs() {
        return getListAs(insertResult);
    }
    public <E> List<E> getUpdateListAs() {
        return getListAs(updateResult);
    }
    public <E> List<E> getDeleteListAs() {
        return getListAs(deleteResult);
    }

    public <E> E getAs(Object result) {
        if (result == null) {
            return null;
        }
        //noinspection unchecked
        return (E) result;
    }

    public <E> Set<E> getSetAs(Object result) {
        if (result == null) {
            return Collections.emptySet();
        }
        @SuppressWarnings("unchecked")
        Set<E> safe = (Set<E>) result;
        return safe;
    }

    public <E> List<E> getListAs(Object result) {
        if (result == null) {
            return Collections.emptyList();
        }
        @SuppressWarnings("unchecked")
        List<E> safe = (List<E>) result;
        return safe;
    }

    public <E> Set<E> getAllSetAs() {
        Set<E> insertSet = getSetAs(insertResult);
        Set<E> updateSet = getSetAs(updateResult);
        Set<E> deleteSet = getSetAs(deleteResult);
        return Stream.of(insertSet, updateSet, deleteSet)
            .flatMap(Set::stream)
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<String> getAllSetKeys() {
        Set<String> setKeys = new LinkedHashSet<>();
        addAll(insertResult, setKeys);
        addAll(updateResult, setKeys);
        addAll(deleteResult, setKeys);
        return setKeys;
    }

    public Set<String> getSetKeys(boolean insert, boolean update, boolean delete) {
        Set<String> setKeys = new LinkedHashSet<>();
        if (insert) {
            addAll(insertResult, setKeys);
        }
        if (update) {
            addAll(updateResult, setKeys);
        }
        if (delete) {
            addAll(deleteResult, setKeys);
        }
        return setKeys;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void addAll(Object result, Set<String> setKeys) {
        if (result == null) {
            return;
        }
        if (result instanceof Triple triple) {
            setKeys.addAll((Collection<? extends String>) triple.getLeft());
            setKeys.addAll((Collection<? extends String>) triple.getMiddle());
            setKeys.addAll((Collection<? extends String>) triple.getRight());
        }
        else if (result instanceof Pair pair) {
            setKeys.addAll((Collection<? extends String>) pair.getLeft());
            setKeys.addAll((Collection<? extends String>) pair.getRight());
        }
        else if (result instanceof Set set) {
            setKeys.addAll(set);
        }
    }
}
