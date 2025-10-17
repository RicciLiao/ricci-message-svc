package ricciliao.message.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.message.pojo.po.MessageCodePo;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface MessageCodeRepository extends JpaRepository<MessageCodePo, Long> {

    @Override
    void flush();

    @Override
    <S extends MessageCodePo> S saveAndFlush(S entity);

    @Override
    <S extends MessageCodePo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<MessageCodePo> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    MessageCodePo getReferenceById(Long aLong);

    @Override
    <S extends MessageCodePo> List<S> findAll(Example<S> example);

    @Override
    <S extends MessageCodePo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends MessageCodePo> List<S> saveAll(Iterable<S> entities);

    @Override
    List<MessageCodePo> findAll();

    @Override
    List<MessageCodePo> findAllById(Iterable<Long> longs);

    @Override
    <S extends MessageCodePo> S save(S entity);

    @Override
    Optional<MessageCodePo> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(MessageCodePo entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends MessageCodePo> entities);

    @Override
    void deleteAll();

    @Override
    List<MessageCodePo> findAll(Sort sort);

    @Override
    Page<MessageCodePo> findAll(Pageable pageable);

    @Override
    <S extends MessageCodePo> Optional<S> findOne(Example<S> example);

    @Override
    <S extends MessageCodePo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends MessageCodePo> long count(Example<S> example);

    @Override
    <S extends MessageCodePo> boolean exists(Example<S> example);

    @Override
    <S extends MessageCodePo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    Optional<MessageCodePo> findByCodeAndConsumer(String code, String consumer);

    @Query("select max(updatedDtm) from MessageCodePo")
    Instant refreshCache();
}
