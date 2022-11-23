package com.api.obra.domain.obra;

import com.api.obra.domain.detalhetecnico.model.DetalhesTecnicosResponse;
import com.api.obra.domain.inspecao.model.ObraInspecaoResponse;
import com.api.obra.domain.localizacao.model.LocalizacaoResponse;
import com.api.obra.domain.obra.model.ObraRequest;
import com.api.obra.domain.obra.model.ObraResponse;
import com.api.obra.repository.obra.ObraRepository;
import com.api.obra.repository.obra.model.ObraEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.api.obra.util.NullPropertyNamesUtil.getNullPropertyNames;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ObraService {

    private final ObraRepository obraRepository;

    public ObraResponse criar(ObraRequest obraRequest) {
        var obraEntity = new ObraEntity();
        BeanUtils.copyProperties(obraRequest, obraEntity);
        obraRepository.save(obraEntity);
        return toModel(obraEntity);
    }

    public Page<ObraResponse> listarTodasObras(Integer page, Integer linesPerPage,
                                               String direction, String orderBy) {
        var pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        var todasObras = obraRepository.findAll(pageRequest);
        return todasObras.map(this::toModel);
    }

    public ObraResponse recuperarObraPorId(Long id) {
        var obraEntity = obraRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObraEntity.class, ObraEntity.class.getName()));
        return toModel(obraEntity);
    }

    public ObraResponse atualizarObraPorId(Long id, ObraRequest obraRequest) {
        var obraEntity = obraRepository.getReferenceById(id);
        var nomeDosAtributosNulosASeremIgnorados = getNullPropertyNames(obraRequest);
        BeanUtils.copyProperties(obraRequest, obraEntity, nomeDosAtributosNulosASeremIgnorados);
        obraRepository.save(obraEntity);
        return toModel(obraEntity);
    }

    public void apagarObraPorId(Long id) {
        obraRepository.deleteById(id);
    }

    private ObraResponse toModel(ObraEntity obraEntity) {
        return ObraResponse.builder()
                .id(obraEntity.getId())
                .nome(obraEntity.getNome())
                .anoConstrucao(obraEntity.getAnoConstrucao())
                .coordenacao(obraEntity.getCoordenacao())
                .gerencia(obraEntity.getGerencia())
                .diretoria(obraEntity.getDiretoria())
                .outorga(obraEntity.getOutorga())
                .titularidade(obraEntity.getTitularidade())
                .localizacao(isNull(obraEntity.getLocalizacao()) ? null : new LocalizacaoResponse(obraEntity.getLocalizacao()))
                .detalhesTecnicos(isNull(obraEntity.getDetalhesTecnicos()) ? null : new DetalhesTecnicosResponse(obraEntity.getDetalhesTecnicos()))
                .obraInspecao(isNull(obraEntity.getObraInspecao()) ? null : new ObraInspecaoResponse(obraEntity.getObraInspecao()))
                .build();
    }
}
