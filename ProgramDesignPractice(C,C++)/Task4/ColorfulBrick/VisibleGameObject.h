#pragma once

class VisibleGameObject
{
public:
	VisibleGameObject();
	virtual ~VisibleGameObject();
	
	virtual void load(std::string filename);
	virtual void draw(sf::RenderWindow & window);
	virtual bool update(float elapsedTime);

	virtual void setPosition(float x, float y);
	virtual void setState(int);

	virtual sf::Vector2f getPosition() const;
	virtual float getWidth() const;
	virtual float getHeight() const;
	virtual int getType() const = 0;
	virtual int getState() const;

	virtual bool isLoaded() const;
	virtual sf::Rect<float> getBoundingRect() const;

protected:
	sf::Sprite& getSprite();

private:
	enum BrickState
	{
		Invisible, Static, Dropping 
	};
	BrickState _brickState;
	float _vx, _vy, _acceleration;
	sf::Sprite  _sprite;
	sf::Texture _texture;
	std::string _filename;
	bool _isLoaded;


};

