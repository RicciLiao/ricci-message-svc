package ricciliao.message.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.message.pojo.po.MessageCodeSecondaryPo;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface MessageCodeSecondaryRepository extends JpaRepository<MessageCodeSecondaryPo, Long> {
    @Override
    void flush();

    @Override
    <S extends MessageCodeSecondaryPo> S saveAndFlush(S entity);

    @Override
    <S extends MessageCodeSecondaryPo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<MessageCodeSecondaryPo> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    MessageCodeSecondaryPo getReferenceById(Long aLong);

    @Override
    <S extends MessageCodeSecondaryPo> List<S> findAll(Example<S> example);

    @Override
    <S extends MessageCodeSecondaryPo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends MessageCodeSecondaryPo> List<S> saveAll(Iterable<S> entities);

    @Override
    List<MessageCodeSecondaryPo> findAll();

    @Override
    List<MessageCodeSecondaryPo> findAllById(Iterable<Long> longs);

    @Override
    <S extends MessageCodeSecondaryPo> S save(S entity);

    @Override
    Optional<MessageCodeSecondaryPo> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(MessageCodeSecondaryPo entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends MessageCodeSecondaryPo> entities);

    @Override
    void deleteAll();

    @Override
    List<MessageCodeSecondaryPo> findAll(Sort sort);

    @Override
    Page<MessageCodeSecondaryPo> findAll(Pageable pageable);

    @Override
    <S extends MessageCodeSecondaryPo> Optional<S> findOne(Example<S> example);

    @Override
    <S extends MessageCodeSecondaryPo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends MessageCodeSecondaryPo> long count(Example<S> example);

    @Override
    <S extends MessageCodeSecondaryPo> boolean exists(Example<S> example);

    @Override
    <S extends MessageCodeSecondaryPo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Query("select max(updatedDtm) from MessageCodeSecondaryPo")
    Instant refreshCache();
}