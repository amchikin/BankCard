package ru.example.BankCard.Mapper;
import ru.example.BankCard.DTO.AbstractDTO;
import ru.example.BankCard.Entity.AbstractEntity;

public interface Mapper <E extends AbstractEntity, D extends AbstractDTO> {
    E toEntity(D dto);

    D toDto(E entity);
}
