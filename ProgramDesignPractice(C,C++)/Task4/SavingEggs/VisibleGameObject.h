#pragma once
#include "stdafx.h"

class VisibleGameObject
{
public:
	VisibleGameObject();
	virtual ~VisibleGameObject();
	
	virtual void load(std::string filename);
	virtual void draw(sf::RenderWindow & window);
	virtual void update(float elapsedTime);

	virtual void setPosition(float x, float y);
	virtual sf::Vector2f getPosition() const;
	virtual float getWidth() const;
	virtual float getHeight() const;

	virtual bool isLoaded() const;
	virtual sf::Rect<float> getBoundingRect() const;

protected:
	sf::Sprite& getSprite();

private:
	sf::Sprite  _sprite;
	sf::Texture _texture;
	std::string _filename;
	bool _isLoaded;
};

