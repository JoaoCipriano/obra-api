package com.api.obra.domain.localizacao;

import com.api.obra.domain.localizacao.model.LocalizacaoRequest;
import com.api.obra.domain.localizacao.model.LocalizacaoResponse;
import com.api.obra.domain.localizacao.model.ObraLocalizacaoResponse;
import com.api.obra.domain.obra.ObraService;
import com.api.obra.repository.localizacao.LocalizacaoRepository;
import com.api.obra.repository.localizacao.model.ObraLocalizacaoEntity;
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
public class LocalizacaoService {

    private final LocalizacaoRepository localizacaoRepository;
    private final ObraService obraService;

    public LocalizacaoResponse criar(LocalizacaoRequest localizacaoRequest, Long obraId) {
        var obraResponse = obraService.recuperarObraPorId(obraId);

        var localizacaoEntity = new ObraLocalizacaoEntity();
        BeanUtils.copyProperties(localizacaoRequest, localizacaoEntity);
        localizacaoEntity.setObra(new ObraEntity(obraResponse.getId()));
        localizacaoRepository.save(localizacaoEntity);

        return new LocalizacaoResponse(localizacaoEntity);
    }

    public Page<ObraLocalizacaoResponse> listarTodasLocalizacoes(Integer page, Integer linesPerPage,
                                                                 String direction, String orderBy) {
        var pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        var todasLocalizacoes = localizacaoRepository.findAll(pageRequest);
        return todasLocalizacoes.map(ObraLocalizacaoResponse::new);
    }

    public ObraLocalizacaoResponse recuperarLocalizacaoPorId(Long id) {
        var localizacaoEntity = localizacaoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObraLocalizacaoEntity.class, ObraLocalizacaoEntity.class.getName()));
        return new ObraLocalizacaoResponse(localizacaoEntity);
    }

    public ObraLocalizacaoResponse atualizarLocalizacaoPorId(Long id, LocalizacaoRequest localizacaoRequest) {
        var localizacaoEntity = localizacaoRepository.getReferenceById(id);
        var nomeDosAtributosNulosASeremIgnorados = getNullPropertyNames(localizacaoRequest);
        BeanUtils.copyProperties(localizacaoRequest, localizacaoEntity, nomeDosAtributosNulosASeremIgnorados);
        localizacaoRepository.save(localizacaoEntity);
        return new ObraLocalizacaoResponse(localizacaoEntity);
    }

    public void apagarLocalizacaoPorId(Long id) {
        localizacaoRepository.deleteById(id);
    }
}
