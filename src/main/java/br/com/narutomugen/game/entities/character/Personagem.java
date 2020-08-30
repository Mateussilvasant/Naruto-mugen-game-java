package br.com.narutomugen.game.entities.character;

import java.util.HashMap;

import br.com.narutomugen.game.animation.Sprite;
import br.com.narutomugen.game.engine.Motor;
import br.com.narutomugen.game.entities.character.actions.Actions;
import br.com.narutomugen.game.entities.character.constants.EDirecao;
import br.com.narutomugen.game.manager.actions.ActionComponent;

public abstract class Personagem {

	protected double eixoX;
	protected double eixoY;
	protected double inicioY;
	protected double velocidade;
	protected EDirecao direcao;
	protected double forca;

	protected HashMap<Integer, Sprite> mapaSprites;
	protected MaquinaEstado controleEstado;

	public Personagem(int x, int y, boolean inverter) {
		mapaSprites = new HashMap<Integer, Sprite>();
		controleEstado = new MaquinaEstado(Actions.values().length);
		inicioY = y;
		forca = 20;
		direcao = inverter ? EDirecao.ESQUERDA : EDirecao.DIREITA;
		setEixoX(x);
		setEixoY(y);
		setVelocidade(150);

		iniciarEstados();
		controleEstado.transitar(Actions.INICIAL);
	}

	private void iniciarEstados() {
		estadoCorrerDireita();
		estadoCorrerEsquerda();
		estadoParado();
		estadoPular();
		estadoInicial();
	}

	private void estadoCorrerDireita() {
		controleEstado.adicionarEstado(new ActionComponent(Actions.CORRER_DIREITA) {

			@Override
			public boolean action() {
				direcao = EDirecao.DIREITA;
				eixoX = eixoX + (getVelocidade() * Motor.getDelta());
				mapaSprites.get(Actions.CORRER_DIREITA.getValue()).animar(getEixoX(), getEixoY());
				return false;
			}
		});
	}

	private void estadoCorrerEsquerda() {

		controleEstado.adicionarEstado(new ActionComponent(Actions.CORRER_ESQUERDA) {

			@Override
			public boolean action() {
				direcao = EDirecao.ESQUERDA;
				eixoX = eixoX - (getVelocidade() * Motor.getDelta());
				mapaSprites.get(Actions.CORRER_ESQUERDA.getValue()).animar(getEixoX(), getEixoY());
				return false;
			}
		});
	}

	private void estadoParado() {
		controleEstado.adicionarEstado(new ActionComponent(Actions.PARADO) {

			@Override
			public boolean action() {
				Sprite paradoSprite = mapaSprites.get(Actions.PARADO.getValue());
				verificarDirecaoSprite(paradoSprite);
				paradoSprite.animar(getEixoX(), getEixoY());
				return true;
			}
		});
	}

	private void estadoPular() {

		controleEstado.adicionarEstado(new ActionComponent(Actions.PULAR) {

			@Override
			public boolean action() {
				Sprite spritePular = mapaSprites.get(Actions.PULAR.getValue());
				verificarDirecaoSprite(spritePular);

				forca--;
				setEixoY(getEixoY() - forca);

				if (getEixoY() == inicioY) {
					forca = 20;
					spritePular.animar(1, getEixoX(), getEixoY());
					controleEstado.transitar(Actions.PARADO);
				} else if (forca > 0) {
					spritePular.animar(2, getEixoX(), getEixoY());

				} else if (forca < 0) {
					spritePular.animar(0, getEixoX(), getEixoY());
				}

				return true;
			}

		});
	}

	private void estadoInicial() {

		controleEstado.adicionarEstado(new ActionComponent(Actions.INICIAL) {

			@Override
			public boolean action() {
				Sprite spriteInicial = mapaSprites.get(Actions.INICIAL.getValue());
				spriteInicial.animar(getEixoX(), getEixoY());

				if (spriteInicial.fimAnimacao()) {
					controleEstado.transitar(Actions.PARADO);
				}
				return true;
			}

		});
	}

	private void verificarDirecaoSprite(Sprite sprite) {

		if (direcao.equals(EDirecao.ESQUERDA)) {
			if (!sprite.getInvertido()) {
				sprite.setInverterAnimacao(true);
			}
		} else {
			if (sprite.getInvertido()) {
				sprite.setInverterAnimacao(false);
			}
		}

	}

	public void parar() {
		if (!controleEstado.verificaEstadoAtual(Actions.INICIAL.getValue())) {
			if (!controleEstado.getEstadoAtual().isRepeat()) {
				controleEstado.transitar(Actions.PARADO);
			}
		}

	}

	public void atualizarMecanicas() {
		System.out.println("Estado Atual: " + controleEstado.getEstadoAtual().getId() + " Estado Anterior: "+ controleEstado.getEstadoAnterior().getId());

		controleEstado.getEstadoAtual().dispatch();


	}

	public abstract void atualizarAnimacoes();

	public void correrParaDireita() {
		controleEstado.transitar(Actions.CORRER_DIREITA);
	}

	public void correrParaEsquerda() {
		controleEstado.transitar(Actions.CORRER_ESQUERDA);
	}

	public void pularParaCima() {
		controleEstado.transitar(Actions.PULAR);
	}

	public abstract void executarPoder_1();

	public double getEixoX() {
		return eixoX;
	}

	public double getEixoY() {
		return eixoY;
	}

	public double getVelocidade() {
		return velocidade;
	}

	public void setEixoX(double x) {
		eixoX = x;
	}

	public void setEixoY(double y) {
		eixoY = y;
	}

	public double getWidth() {
		return mapaSprites.get(controleEstado.getEstadoAnterior().getId()).getWidth();
	}

	public void setVelocidade(double velocidade) {
		this.velocidade = velocidade;
	}

}
