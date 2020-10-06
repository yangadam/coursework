#include "stdafx.hpp"
#include "VisibleGameObject.hpp"


VisibleGameObject::VisibleGameObject() 
{
	_vx = rand()%61-30;
	_vy = -60;
	_acceleration = 30;
	_brickState = Static;
	_isLoaded = false;
	//std::cout << "base" << std::endl;
}


VisibleGameObject::~VisibleGameObject()
{
	
}

void VisibleGameObject::load(std::string filename)
{
	if(!_texture.loadFromFile(filename))
	{
		_filename =  "";
		_isLoaded = false;
	}
	else
	{
		_filename = filename;
		_sprite.setTexture(_texture);
		_isLoaded = true;
	}
}

void VisibleGameObject::draw(sf::RenderWindow & renderWindow)
{
	if(_isLoaded)
	{
		renderWindow.draw(_sprite);
	}
}


bool VisibleGameObject::update(float elapsedTime)
{
	if(_brickState == Static)
		return false;
	else if (_brickState == Dropping)
	{
		if (getSprite().getPosition().x<-80 || getSprite().getPosition().x>1024 || getSprite().getPosition().y>768)
		{
			_brickState = VisibleGameObject::Invisible;
		}
		getSprite().rotate(_vx*elapsedTime*5);
		getSprite().move(_vx*elapsedTime*10, _vy*elapsedTime*10);
		_vy += _acceleration*elapsedTime*10;
		return false;
	}
	return true;

}

void VisibleGameObject::setPosition(float x, float y)
{
	if(_isLoaded)
	{
		_sprite.setPosition(x,y);
	}
}

void VisibleGameObject::setState(int state)
{
	_brickState = (BrickState)state;
}

sf::Vector2f VisibleGameObject::getPosition() const
{
	if(_isLoaded)
	{
		return _sprite.getPosition();
	}
	return sf::Vector2f();
}

float VisibleGameObject::getHeight() const
{
	return _sprite.getTextureRect().height;
}

float VisibleGameObject::getWidth() const
{
	return _sprite.getTextureRect().width;
}

sf::Rect<float> VisibleGameObject::getBoundingRect() const
{
	sf::Vector2f size = sf::Vector2f(_sprite.getTextureRect().width, _sprite.getTextureRect().height);
	sf::Vector2f position = _sprite.getPosition();

	return sf::Rect<float>(
						position.x - size.x/2,
						position.y - size.y/2,
						position.x + size.x/2,
						position.y + size.y/2
						);
}

sf::Sprite& VisibleGameObject::getSprite()
{
	return _sprite;
}

int VisibleGameObject::getState() const
{
	return (int)_brickState;
}

bool VisibleGameObject::isLoaded() const
{
	return _isLoaded;
}