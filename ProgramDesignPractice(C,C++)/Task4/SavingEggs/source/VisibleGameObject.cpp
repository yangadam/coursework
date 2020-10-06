#include "stdafx.hpp"
#include "VisibleGameObject.hpp"


VisibleGameObject::VisibleGameObject() 
{
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


void VisibleGameObject::update(float elapsedTime)
{
}

void VisibleGameObject::setPosition(float x, float y)
{
	if(_isLoaded)
	{
		_sprite.setPosition(x,y);
	}
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

bool VisibleGameObject::isLoaded() const
{
	return _isLoaded;
}