package ricciliao.message.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.message.pojo.po.MessageCodePrimaryPo;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface MessageCodePrimaryRepository extends JpaRepository<MessageCodePrimaryPo, Long> {
    @Override
    void flush();

    @Override
    <S extends MessageCodePrimaryPo> S saveAndFlush(S entity);

    @Override
    <S extends MessageCodePrimaryPo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<MessageCodePrimaryPo> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    MessageCodePrimaryPo getReferenceById(Long aLong);

    @Override
    <S extends MessageCodePrimaryPo> List<S> findAll(Example<S> example);

    @Override
    <S extends MessageCodePrimaryPo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends MessageCodePrimaryPo> List<S> saveAll(Iterable<S> entities);

    @Override
    List<MessageCodePrimaryPo> findAll();

    @Override
    List<MessageCodePrimaryPo> findAllById(Iterable<Long> longs);

    @Override
    <S extends MessageCodePrimaryPo> S save(S entity);

    @Override
    Optional<MessageCodePrimaryPo> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(MessageCodePrimaryPo entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends MessageCodePrimaryPo> entities);

    @Override
    void deleteAll();

    @Override
    List<MessageCodePrimaryPo> findAll(Sort sort);

    @Override
    Page<MessageCodePrimaryPo> findAll(Pageable pageable);

    @Override
    <S extends MessageCodePrimaryPo> Optional<S> findOne(Example<S> example);

    @Override
    <S extends MessageCodePrimaryPo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends MessageCodePrimaryPo> long count(Example<S> example);

    @Override
    <S extends MessageCodePrimaryPo> boolean exists(Example<S> example);

    @Override
    <S extends MessageCodePrimaryPo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Query("select max(updatedDtm) from MessageCodePrimaryPo")
    Instant refreshCache();

}