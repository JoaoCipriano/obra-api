package com.api.obra.domain.detalhetecnico;

import com.api.obra.domain.detalhetecnico.model.DetalhesTecnicosResponse;
import com.api.obra.domain.detalhetecnico.model.DetalhesTecnicosRequest;
import com.api.obra.domain.detalhetecnico.model.ObraDetalhesTecnicosResponse;
import com.api.obra.domain.obra.ObraService;
import com.api.obra.repository.detalhetecnico.DetalheTecnicoRepository;
import com.api.obra.repository.detalhetecnico.model.ObraDetalheTecnicoEntity;
import com.api.obra.repository.obra.model.ObraEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.api.obra.util.NullPropertyNamesUtil.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class DetalheTecnicoService {

    private final DetalheTecnicoRepository detalheTecnicoRepository;
    private final ObraService obraService;

    public DetalhesTecnicosResponse criar(DetalhesTecnicosRequest detalhesTecnicosRequest, Long obraId) {
        var obraResponse = obraService.recuperarObraPorId(obraId);

        var obraDetalheTecnicoEntity = new ObraDetalheTecnicoEntity();
        BeanUtils.copyProperties(detalhesTecnicosRequest, obraDetalheTecnicoEntity);
        obraDetalheTecnicoEntity.setObra(new ObraEntity(obraResponse.getId()));
        detalheTecnicoRepository.save(obraDetalheTecnicoEntity);

        return new DetalhesTecnicosResponse(obraDetalheTecnicoEntity);
    }

    public Page<ObraDetalhesTecnicosResponse> listarTodosDetalhesTecnicos(Integer page, Integer linesPerPage,
                                                                          String direction, String orderBy) {
        var pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        var todosDetalhesTecnicos = detalheTecnicoRepository.findAll(pageRequest);
        return todosDetalhesTecnicos.map(ObraDetalhesTecnicosResponse::new);
    }

    public ObraDetalhesTecnicosResponse recuperarDetalhesTecnicosPorId(Long id) {
        var obraDetalheTecnicoEntity = detalheTecnicoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObraDetalheTecnicoEntity.class, ObraDetalheTecnicoEntity.class.getName()));
        return new ObraDetalhesTecnicosResponse(obraDetalheTecnicoEntity);
    }

    public ObraDetalhesTecnicosResponse atualizarDetalhesTecnicos(Long id, DetalhesTecnicosRequest detalhesTecnicosRequest) {
        var obraDetalheTecnicoEntity = detalheTecnicoRepository.getReferenceById(id);
        var nomeDosAtributosNulosASeremIgnorados = getNullPropertyNames(detalhesTecnicosRequest);
        BeanUtils.copyProperties(detalhesTecnicosRequest, obraDetalheTecnicoEntity, nomeDosAtributosNulosASeremIgnorados);
        detalheTecnicoRepository.save(obraDetalheTecnicoEntity);
        return new ObraDetalhesTecnicosResponse(obraDetalheTecnicoEntity);
    }

    public void apagarDetalhesTecnicos(Long id) {
        detalheTecnicoRepository.deleteById(id);
    }
}
